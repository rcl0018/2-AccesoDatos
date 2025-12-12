/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectdbraulcamaralara;

/**
 *
 * @author PC205
 */
@Entity
public class Empleado extends Persona {
    private double salario;

    @ManyToOne
    Departamento departamento;

    public Empleado() {}

    public Empleado(int id, String nombre, int edad, double salario, Departamento d) {
        super(id, nombre, edad);
        this.salario = salario;
        this.departamento = d;
    }  
}