package com.example.ProductFinder.modelo;

import javax.persistence.*;

@Entity
public class Pasillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 60,nullable = false,unique = true)
    private String nombre;

    public Pasillo() {
    }

    public Pasillo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
