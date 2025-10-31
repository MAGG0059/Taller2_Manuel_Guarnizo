package edu.dosw.Services;

import edu.dosw.model.*;
import edu.dosw.repositories.RecetaRepository;
import edu.dosw.services.RecetaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaService recetaService;

    @Test
    void registrar_ConTipoChef_DeberiaUsarChefStrategy() {
        Receta recetaEsperada = new Receta.Builder()
                .titulo("Receta Chef")
                .chef(new Chef("Chef Test", "chef", 1))
                .build();

        when(recetaRepository.save(any(Receta.class))).thenReturn(recetaEsperada);

        Receta resultado = recetaService.registrar("chef", "Receta Chef",
                Arrays.asList("ing1", "ing2"), "Preparación", "Chef Test", 1);

        assertNotNull(resultado);
        verify(recetaRepository).save(any(Receta.class));
    }

    @Test
    void registrar_ConTipoParticipante_DeberiaUsarParticipanteStrategy() {
        Receta recetaEsperada = new Receta.Builder()
                .titulo("Receta Participante")
                .chef(new Chef("Part Test", "participante", 1))
                .build();

        when(recetaRepository.save(any(Receta.class))).thenReturn(recetaEsperada);

        Receta resultado = recetaService.registrar("participante", "Receta Participante",
                Arrays.asList("ing1", "ing2"), "Preparación", "Part Test", 1);

        assertNotNull(resultado);
        verify(recetaRepository).save(any(Receta.class));
    }

    @Test
    void registrar_ConTipoDesconocido_DeberiaUsarTelevidenteStrategy() {
        Receta recetaEsperada = new Receta.Builder()
                .titulo("Receta Televidente")
                .chef(new Chef("TV Test", "televidente", null))
                .build();

        when(recetaRepository.save(any(Receta.class))).thenReturn(recetaEsperada);

        Receta resultado = recetaService.registrar("desconocido", "Receta Televidente",
                Arrays.asList("ing1", "ing2"), "Preparación", "TV Test", 1);

        assertNotNull(resultado);
        verify(recetaRepository).save(any(Receta.class));
    }

    @Test
    void obtenerTodas_DeberiaRetornarListaRecetas() {
        List<Receta> recetas = Arrays.asList(
                new Receta.Builder().titulo("Receta 1").build(),
                new Receta.Builder().titulo("Receta 2").build()
        );

        when(recetaRepository.findAll()).thenReturn(recetas);

        List<Receta> resultado = recetaService.obtenerTodas();

        assertEquals(2, resultado.size());
        verify(recetaRepository).findAll();
    }

    @Test
    void obtenerPorId_RecetaExistente_DeberiaRetornarReceta() {
        Receta receta = new Receta.Builder().titulo("Receta Test").build();
        receta.setId("1");

        when(recetaRepository.findById("1")).thenReturn(Optional.of(receta));

        Optional<Receta> resultado = recetaService.obtenerPorId("1");

        assertTrue(resultado.isPresent());
        assertEquals("1", resultado.get().getId());
        verify(recetaRepository).findById("1");
    }

    @Test
    void obtenerPorId_RecetaNoExistente_DeberiaRetornarEmpty() {
        when(recetaRepository.findById("999")).thenReturn(Optional.empty());

        Optional<Receta> resultado = recetaService.obtenerPorId("999");

        assertFalse(resultado.isPresent());
        verify(recetaRepository).findById("999");
    }

    @Test
    void obtenerPorTipo_DeberiaRetornarRecetasDelTipo() {
        List<Receta> recetasChef = Arrays.asList(
                new Receta.Builder().titulo("Receta Chef 1").build(),
                new Receta.Builder().titulo("Receta Chef 2").build()
        );

        when(recetaRepository.findByChef_Tipo("chef")).thenReturn(recetasChef);

        List<Receta> resultado = recetaService.obtenerPorTipo("chef");

        assertEquals(2, resultado.size());
        verify(recetaRepository).findByChef_Tipo("chef");
    }

    @Test
    void obtenerPorTemporada_DeberiaRetornarRecetasDeParticipantesEnTemporada() {
        List<Receta> recetasTemporada = Arrays.asList(
                new Receta.Builder().titulo("Receta Temp 1").build(),
                new Receta.Builder().titulo("Receta Temp 2").build()
        );

        when(recetaRepository.findByChef_TipoAndChef_Temporada("participante", 3))
                .thenReturn(recetasTemporada);

        List<Receta> resultado = recetaService.obtenerPorTemporada(3);

        assertEquals(2, resultado.size());
        verify(recetaRepository).findByChef_TipoAndChef_Temporada("participante", 3);
    }

    @Test
    void buscarPorIngrediente_DeberiaRetornarRecetasConIngrediente() {
        List<Receta> recetasConTomate = Arrays.asList(
                new Receta.Builder().titulo("Ensalada").ingredientes(Arrays.asList("tomate", "lechuga")).build(),
                new Receta.Builder().titulo("Salsa").ingredientes(Arrays.asList("tomate", "cebolla")).build()
        );

        when(recetaRepository.findByIngredientesContaining("tomate")).thenReturn(recetasConTomate);

        List<Receta> resultado = recetaService.buscarPorIngrediente("tomate");

        assertEquals(2, resultado.size());
        verify(recetaRepository).findByIngredientesContaining("tomate");
    }

    @Test
    void eliminar_DeberiaEliminarReceta() {
        doNothing().when(recetaRepository).deleteById("1");

        recetaService.eliminar("1");

        verify(recetaRepository).deleteById("1");
    }

    @Test
    void actualizar_RecetaExistente_DeberiaActualizarYRetornarReceta() {
        Receta recetaExistente = new Receta.Builder()
                .titulo("Título Original")
                .ingredientes(Arrays.asList("ing1"))
                .preparacion("Prep Original")
                .chef(new Chef("Chef Original", "chef", 1))
                .build();
        recetaExistente.setId("1");

        Receta recetaNueva = new Receta.Builder()
                .titulo("Título Actualizado")
                .ingredientes(Arrays.asList("nuevo ing"))
                .preparacion("Nueva prep")
                .chef(new Chef("Chef Actualizado", "chef", 2))
                .build();

        when(recetaRepository.findById("1")).thenReturn(Optional.of(recetaExistente));
        when(recetaRepository.save(any(Receta.class))).thenReturn(recetaExistente);

        Receta resultado = recetaService.actualizar("1", recetaNueva);

        assertNotNull(resultado);
        verify(recetaRepository).findById("1");
        verify(recetaRepository).save(recetaExistente);
    }

    @Test
    void actualizar_RecetaNoExistente_DeberiaLanzarExcepcion() {
        Receta recetaNueva = new Receta.Builder().titulo("Receta Nueva").build();

        when(recetaRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> recetaService.actualizar("999", recetaNueva));
        verify(recetaRepository).findById("999");
        verify(recetaRepository, never()).save(any(Receta.class));
    }
}
