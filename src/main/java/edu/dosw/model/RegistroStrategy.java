package edu.dosw.model;

import edu.dosw.model.Receta;
import java.util.List;

public interface RegistroStrategy {
    Receta registrar(String titulo, List<String> ingredientes, String preparacion, String nombre, Integer temporada);
}
