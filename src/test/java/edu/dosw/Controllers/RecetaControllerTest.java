package edu.dosw.Controllers;

import edu.dosw.controller.RecetaController;
import edu.dosw.dto.RecetaRequest;
import edu.dosw.model.Chef;
import edu.dosw.model.Receta;
import edu.dosw.services.RecetaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaControllerTest {

    @Mock
    private RecetaService recetaService;

    @InjectMocks
    private RecetaController recetaController;

    @Test
    void registrarReceta_DeberiaRetornarRecetaCreada() {
        RecetaRequest request = new RecetaRequest("Título Test", Arrays.asList("ing1", "ing2"), "Preparación test", "Chef Test", 1);
        Receta recetaEsperada = new Receta.Builder()
                .titulo("Título Test")
                .build();
        recetaEsperada.setId("1");

        when(recetaService.registrar("chef", "Título Test", Arrays.asList("ing1", "ing2"), "Preparación test", "Chef Test", 1))
                .thenReturn(recetaEsperada);

        ResponseEntity<Receta> respuesta = recetaController.registrarReceta("chef", request);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("1", respuesta.getBody().getId());
    }

    @Test
    void obtenerTodas_DeberiaRetornarListaRecetas() {
        List<Receta> recetas = Arrays.asList(
                new Receta.Builder().titulo("Receta 1").build(),
                new Receta.Builder().titulo("Receta 2").build()
        );

        when(recetaService.obtenerTodas()).thenReturn(recetas);

        ResponseEntity<List<Receta>> respuesta = recetaController.obtenerTodas();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void obtenerPorId_RecetaExistente_DeberiaRetornarReceta() {
        Receta receta = new Receta.Builder().titulo("Receta Test").build();
        receta.setId("1");

        when(recetaService.obtenerPorId("1")).thenReturn(Optional.of(receta));

        ResponseEntity<Receta> respuesta = recetaController.obtenerPorId("1");

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("1", respuesta.getBody().getId());
    }

    @Test
    void obtenerPorId_RecetaNoExistente_DeberiaRetornarNotFound() {
        when(recetaService.obtenerPorId("999")).thenReturn(Optional.empty());

        ResponseEntity<Receta> respuesta = recetaController.obtenerPorId("999");

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
    }

    @Test
    void obtenerDeParticipantes_DeberiaRetornarListaParticipantes() {
        List<Receta> recetasParticipantes = Arrays.asList(
                new Receta.Builder().titulo("Receta Part 1").build(),
                new Receta.Builder().titulo("Receta Part 2").build()
        );

        when(recetaService.obtenerPorTipo("participante")).thenReturn(recetasParticipantes);

        ResponseEntity<List<Receta>> respuesta = recetaController.obtenerDeParticipantes();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void obtenerDeTelevidentes_DeberiaRetornarListaTelevidentes() {
        List<Receta> recetasTelevidentes = Arrays.asList(
                new Receta.Builder().titulo("Receta TV 1").build(),
                new Receta.Builder().titulo("Receta TV 2").build()
        );

        when(recetaService.obtenerPorTipo("televidente")).thenReturn(recetasTelevidentes);

        ResponseEntity<List<Receta>> respuesta = recetaController.obtenerDeTelevidentes();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void obtenerDeChefs_DeberiaRetornarListaChefs() {
        List<Receta> recetasChefs = Arrays.asList(
                new Receta.Builder().titulo("Receta Chef 1").build(),
                new Receta.Builder().titulo("Receta Chef 2").build()
        );

        when(recetaService.obtenerPorTipo("chef")).thenReturn(recetasChefs);

        ResponseEntity<List<Receta>> respuesta = recetaController.obtenerDeChefs();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void obtenerPorTemporada_DeberiaRetornarRecetasTemporada() {
        List<Receta> recetasTemporada = Arrays.asList(
                new Receta.Builder().titulo("Receta Verano").build(),
                new Receta.Builder().titulo("Receta Verano 2").build()
        );

        when(recetaService.obtenerPorTemporada(3)).thenReturn(recetasTemporada);

        ResponseEntity<List<Receta>> respuesta = recetaController.obtenerPorTemporada(3);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void buscarPorIngrediente_DeberiaRetornarRecetasConIngrediente() {
        List<Receta> recetasConTomate = Arrays.asList(
                new Receta.Builder().titulo("Ensalada").ingredientes(Arrays.asList("tomate", "lechuga")).build(),
                new Receta.Builder().titulo("Salsa").ingredientes(Arrays.asList("tomate", "cebolla")).build()
        );

        when(recetaService.buscarPorIngrediente("tomate")).thenReturn(recetasConTomate);

        ResponseEntity<List<Receta>> respuesta = recetaController.buscarPorIngrediente("tomate");

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(2, respuesta.getBody().size());
    }

    @Test
    void eliminar_DeberiaEliminarRecetaYRetornarNoContent() {
        doNothing().when(recetaService).eliminar("1");

        ResponseEntity<Void> respuesta = recetaController.eliminar("1");

        assertEquals(HttpStatus.NO_CONTENT, respuesta.getStatusCode());
        assertNull(respuesta.getBody());
    }

    @Test
    void actualizar_DeberiaRetornarRecetaActualizada() {
        Chef chef = new Chef("Chef Actual", "chef", 2);
        Receta recetaActualizada = new Receta.Builder()
                .titulo("Título Actualizado")
                .ingredientes(Arrays.asList("nuevo ing"))
                .preparacion("Nueva prep")
                .chef(chef)
                .build();
        recetaActualizada.setId("1");

        when(recetaService.actualizar("1", recetaActualizada)).thenReturn(recetaActualizada);

        ResponseEntity<Receta> respuesta = recetaController.actualizar("1", recetaActualizada);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Título Actualizado", respuesta.getBody().getTitulo());
    }
}