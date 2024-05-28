package com.example.ProductFinder.modelo;

import javax.persistence.*;

@Entity
public class Bodegas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45,nullable = false,unique = true)
    private String nombre;

    @Column(length = 45,nullable = false)
    private String temperatura;

    public Bodegas() {
    }

    public Bodegas(Integer id, String nombre, String temperatura) {
        this.id = id;
        this.nombre = nombre;
        this.temperatura = temperatura;
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

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
