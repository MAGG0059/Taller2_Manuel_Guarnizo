package edu.dosw.services;

import edu.dosw.model.Chef;
import edu.dosw.model.Receta;
import edu.dosw.repositories.RecetaRepository;
import edu.dosw.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public Receta registrar(String tipo, String titulo, List<String> ingredientes,
                            String preparacion, String nombre, Integer temporada) {

        RegistroStrategy strategy = switch (tipo.toLowerCase()) {
            case "chef" -> new ChefStrategy();
            case "participante" -> new ParticipanteStrategy();
            default -> new TelevidenteStrategy();
        };

        Receta receta = strategy.registrar(titulo, ingredientes, preparacion, nombre, temporada);
        return recetaRepository.save(receta);
    }

    public List<Receta> obtenerTodas() { return recetaRepository.findAll(); }

    public Optional<Receta> obtenerPorId(String id) { return recetaRepository.findById(id); }

    public List<Receta> obtenerPorTipo(String tipo) {
        return recetaRepository.findByChef_Tipo(tipo);
    }

    public List<Receta> obtenerPorTemporada(int temporada) {
        return recetaRepository.findByChef_TipoAndChef_Temporada("participante", temporada);
    }


    public List<Receta> buscarPorIngrediente(String ingrediente) {
        return recetaRepository.findByIngredientesContaining(ingrediente);
    }

    public void eliminar(String id) { recetaRepository.deleteById(id); }

    public Receta actualizar(String id, Receta nueva) {
        return recetaRepository.findById(id)
                .map(r -> {
                    r.setTitulo(nueva.getTitulo());
                    r.setIngredientes(nueva.getIngredientes());
                    r.setPreparacion(nueva.getPreparacion());
                    Chef chef = r.getChef();
                    if (nueva.getChef() != null) {
                        chef.setNombre(nueva.getChef().getNombre());
                        chef.setTipo(nueva.getChef().getTipo());
                        chef.setTemporada(nueva.getChef().getTemporada());
                    }
                    r.setChef(chef);
                    return recetaRepository.save(r);
                })
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }
}

