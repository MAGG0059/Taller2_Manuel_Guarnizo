package edu.dosw.controller;

import edu.dosw.dto.RecetaRequest;
import edu.dosw.model.Receta;
import edu.dosw.services.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @PostMapping("/registrar/{tipo}")
    public ResponseEntity<Receta> registrarReceta(
            @PathVariable String tipo,
            @RequestBody RecetaRequest request) {

        Receta nueva = recetaService.registrar(
                tipo,
                request.titulo(),
                request.ingredientes(),
                request.preparacion(),
                request.nombre(),
                request.temporada()
        );
        return ResponseEntity.ok(nueva);
    }


    @GetMapping
    public ResponseEntity<List<Receta>> obtenerTodas() {
        return ResponseEntity.ok(recetaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerPorId(@PathVariable String id) {
        return recetaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/participantes")
    public ResponseEntity<List<Receta>> obtenerDeParticipantes() {
        return ResponseEntity.ok(recetaService.obtenerPorTipo("participante"));
    }

    @GetMapping("/televidentes")
    public ResponseEntity<List<Receta>> obtenerDeTelevidentes() {
        return ResponseEntity.ok(recetaService.obtenerPorTipo("televidente"));
    }

    @GetMapping("/chefs")
    public ResponseEntity<List<Receta>> obtenerDeChefs() {
        return ResponseEntity.ok(recetaService.obtenerPorTipo("chef"));
    }

    @GetMapping("/temporada/{temporada}")
    public ResponseEntity<List<Receta>> obtenerPorTemporada(@PathVariable int temporada) {
        return ResponseEntity.ok(recetaService.obtenerPorTemporada(temporada));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Receta>> buscarPorIngrediente(@RequestParam String ingrediente) {
        return ResponseEntity.ok(recetaService.buscarPorIngrediente(ingrediente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        recetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable String id, @RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.actualizar(id, receta));
    }
}
