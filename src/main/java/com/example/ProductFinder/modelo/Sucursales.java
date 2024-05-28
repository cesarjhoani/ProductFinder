package com.example.ProductFinder.modelo;

import javax.persistence.*;

@Entity
public class Sucursales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45,nullable = false,unique = true)
    private String nombre;

    @Column(length = 45,nullable = false)
    private String telefono;
    @Column(length = 45,nullable = false)
    private String dirrecion;

    @Column(length = 354,nullable = false)
    private String ubicacion;

    public Sucursales() {
    }

    public Sucursales(Integer id, String nombre, String telefono, String dirrecion,String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dirrecion = dirrecion;
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }
}
