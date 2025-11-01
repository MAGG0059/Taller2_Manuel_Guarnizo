package edu.dosw.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Taller2 API - Manuel Guamizo - <a href='/swagger-ui.html'>View Swagger Documentation</a>";
    }

    @GetMapping("/health")
    public String health() {
        return "OK - Application is running";
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint working!";
    }
}