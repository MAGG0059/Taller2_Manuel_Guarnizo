package edu.dosw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "recetas")
public class Receta {
    @Id
    private String id;
    private String titulo;
    private List<String> ingredientes;
    private String preparacion;
    private Chef chef;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<String> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<String> ingredientes) { this.ingredientes = ingredientes; }

    public String getPreparacion() { return preparacion; }
    public void setPreparacion(String preparacion) { this.preparacion = preparacion; }

    public Chef getChef() { return chef; }
    public void setChef(Chef chef) { this.chef = chef; }

    public static class Builder {
        private Receta receta = new Receta();

        public Builder titulo(String titulo) { receta.titulo = titulo; return this; }
        public Builder ingredientes(List<String> ingredientes) { receta.ingredientes = ingredientes; return this; }
        public Builder preparacion(String preparacion) { receta.preparacion = preparacion; return this; }
        public Builder chef(Chef chef) { receta.chef = chef; return this; }

        public Receta build() { return receta; }
    }
}