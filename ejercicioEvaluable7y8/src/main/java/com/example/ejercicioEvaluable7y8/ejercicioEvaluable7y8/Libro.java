package com.example.ejercicioEvaluable7y8.ejercicioEvaluable7y8;

import java.util.HashSet;
import java.util.Set;

public class Libro {
    private int idLibro;
    private String titulo;
    private int anioPublicacion;

    // Relación muchos a uno con Autor
    private Autor autor;

    // Relación muchos a muchos con Genero
    private Set<Genero> generos = new HashSet<>();

    public Libro() {
    }

    // Getters y Setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }
}
