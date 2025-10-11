package edu.dosw.model;

import java.util.List;

public class ChefStrategy implements RegistroStrategy {

    @Override
    public Receta registrar(String titulo, List<String> ingredientes, String preparacion,
                            String nombre, Integer temporada) {

        Chef chef = new Chef(nombre, "chef", temporada);
        return new Receta.Builder()
                .titulo(titulo)
                .ingredientes(ingredientes)
                .preparacion(preparacion)
                .chef(chef)
                .build();
    }
}

