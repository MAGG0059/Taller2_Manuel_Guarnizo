package edu.dosw.model;

import edu.dosw.model.Chef;
import edu.dosw.model.Receta;
import java.util.List;

public class ParticipanteStrategy implements RegistroStrategy {
    @Override
    public Receta registrar(String titulo, List<String> ingredientes, String preparacion, String nombre, Integer temporada) {
        Chef chef = new Chef(nombre, "participante", temporada);
        return new Receta.Builder()
                .titulo(titulo)
                .ingredientes(ingredientes)
                .preparacion(preparacion)
                .chef(chef)
                .build();
    }
}
