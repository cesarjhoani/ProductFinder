package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {
    public void guardar(Producto producto);

    public Producto obtenerProductoPorID(Integer id);

    public Page<Producto> obtenerListaProductos(Pageable pageable,String palabraClave);
    public List<Producto> obtenerUltimosProductos();
}

