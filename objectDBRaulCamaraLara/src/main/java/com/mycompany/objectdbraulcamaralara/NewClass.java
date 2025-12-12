/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objectdbraulcamaralara;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC205
 */
public class NewClass {
    public static void main(String[] args) {
        
        // Ruta directa al archivo .odb
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("C:\\Users\\Usuario\\Desktop\\objectdb-2.9.4\\db\\prueba.odb");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Departamento inf = new Departamento(1,"Informática");
        Departamento rrhh = new Departamento(2,"RRHH");

        Empleado e1 = new Empleado(1,"Ana", 30, 1500, inf);
        Empleado e2 = new Empleado(2,"Luis", 45, 2000, inf);
        Empleado e3 = new Empleado(3,"Marta", 28, 1300, rrhh);
        Empleado e4 = new Empleado(4,"Pedro", 32, 1200, rrhh);

        em.persist(inf);
        em.persist(rrhh);

        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(e4);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Base de datos creada con éxito.");
        
    }
    
    
}
