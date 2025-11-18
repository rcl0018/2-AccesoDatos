package com.example.ejercicioEvaluable7y8.ejercicioEvaluable7y8;

import java.util.HashSet;
import java.util.Set;

public class Genero {
    private int idGenero;
    private String nombre;

    // Relación muchos a muchos con Libro (lado inverso)
    private Set<Libro> libros = new HashSet<>();

    public Genero() {
    }

    // Getters y Setters
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}
