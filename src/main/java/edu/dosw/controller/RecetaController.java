package edu.dosw.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private final RecetaService service;
    public RecetaController(RecetaService service) {
        this.service = service;
    }

    @PostMapping("/televidente")
    public ResponseEntity<Receta> crearTelevidente(@RequestBody RecetaDTO dto) {
        return ResponseEntity.ok(service.crearReceta(dto, "TELEVIDENTE"));
    }

    @PostMapping("/participante")
    public ResponseEntity<Receta> crearParticipante(@RequestBody RecetaDTO dto) {
        return ResponseEntity.ok(service.crearReceta(dto, "PARTICIPANTE"));
    }

    @PostMapping("/chef")
    public ResponseEntity<Receta> crearChef(@RequestBody RecetaDTO dto) {
        return ResponseEntity.ok(service.crearReceta(dto, "CHEF"));
    }

    @GetMapping
    public List<Receta> todas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Receta porId(@PathVariable String id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/participantes")
    public List<Receta> participantes() {
        return service.obtenerPorTipo("PARTICIPANTE");
    }

    @GetMapping("/televidentes")
    public List<Receta> televidentes() {
        return service.obtenerPorTipo("TELEVIDENTE");
    }

    @GetMapping("/chefs")
    public List<Receta> chefs() {
        return service.obtenerPorTipo("CHEF");
    }

    @GetMapping("/temporada/{n}")
    public List<Receta> porTemporada(@PathVariable Integer n) {
        return service.obtenerPorTemporada(n);
    }

    @GetMapping("/ingrediente/{nombre}")
    public List<Receta> porIngrediente(@PathVariable String nombre) {
        return service.buscarPorIngrediente(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable String id, @RequestBody RecetaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
}
