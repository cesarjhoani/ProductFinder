package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Producto;

public interface ProductoService {
    public void guardar(Producto producto);

    public Producto obtenerProductoPorID(Integer id);
}
