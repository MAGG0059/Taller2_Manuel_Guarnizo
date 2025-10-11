package edu.dosw.dto;

import java.util.List;

public record RecetaRequest(
        String titulo,
        List<String> ingredientes,
        String preparacion,
        String nombre,
        Integer temporada
) {}
