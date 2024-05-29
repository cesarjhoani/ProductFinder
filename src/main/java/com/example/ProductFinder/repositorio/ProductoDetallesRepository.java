package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.ProductoDetalles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoDetallesRepository extends JpaRepository<ProductoDetalles,Integer> {
    List<ProductoDetalles> findByProductoId(Integer productoId);
}
