package com.example.ProductFinder.modelo;

import javax.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128,nullable = false,unique = true)
    private String nombre;
    @Column(length = 225,nullable = false,unique = true)
    private String codigoBarras;
    @Column(length = 225)
    private String imagen;
    @Column(length = 2)
    private String nivel;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @ManyToOne
    @JoinColumn(name = "pasillo_id")
    private Pasillo pasillo;






}
