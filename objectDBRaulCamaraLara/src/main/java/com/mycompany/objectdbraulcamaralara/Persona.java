/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectdbraulcamaralara;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author PC205
 */
@Entity
public class Persona {
    @Id
    private int id;
    private String nombre;
    private int edad;

    public Persona() {
    }
    public Persona(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
}


