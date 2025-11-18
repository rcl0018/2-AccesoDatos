package com.example.ejercicioEvaluable7y8.ejercicioEvaluable7y8;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Autor {
    private int idAutor;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;

    // Relación uno a muchos con Libro
    private Set<Libro> libros = new HashSet<>();

    public Autor() {
    }

    // Getters y Setters
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}
