package edu.dosw.model;

public class Chef {
    private String nombre;
    private String tipo;
    private Integer temporada;


    public Chef(String nombre, String tipo, Integer temporada) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.temporada = temporada;
    }


    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getTemporada() { return temporada; }
    public void setTemporada(Integer temporada) { this.temporada = temporada; }
}