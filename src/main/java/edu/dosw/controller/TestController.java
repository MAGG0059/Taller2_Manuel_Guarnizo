package edu.dosw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Test API", description = "Endpoints de prueba")
public class TestController {

    @GetMapping("/hello")
    @Operation(summary = "Saludo de prueba", description = "Retorna un mensaje de saludo")
    public String hello() {
        return "¡Hola desde Spring Boot con Swagger!";
    }

    @PostMapping("/echo")
    @Operation(summary = "Eco de mensaje", description = "Retorna el mismo mensaje enviado")
    public String echo(@RequestBody String message) {
        return "Eco: " + message;
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Obtener usuario por ID")
    public String getUser(@PathVariable String id) {
        return "Usuario ID: " + id;
    }
}