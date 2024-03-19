package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Producto;
import com.example.ProductFinder.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }
}
