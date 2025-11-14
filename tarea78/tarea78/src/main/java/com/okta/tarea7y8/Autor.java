package com.okta.tarea7y8;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="autor")
public class Autor {
    




    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @OneToMany(mappedBy="autor", cascade=CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public Autor() {}
    public Autor(String nombre) { this.nombre = nombre; }

    // getters y setters

}
