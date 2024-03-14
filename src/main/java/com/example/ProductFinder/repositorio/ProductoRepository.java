package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
