package com.example.ProductFinder.modelo;

import javax.persistence.*;

//@Entity
public class Producto {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(length = 128,nullable = false,unique = true)
    private String nombre;

}
