/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectdbraulcamaralara;

import javax.persistence.Entity;
import javax.persistence.Id;


    @Entity
public class Departamento {
    @Id
    private int id;
    private String nombre;

    public Departamento() {
    }
    
    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    } 
}
    

