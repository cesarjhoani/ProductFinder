package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1%" +
            "OR p.codigoBarras LIKE %?1%" +
            "OR p.codigoBarras LIKE %?1%")
    public Page<Producto> filtrarProducto(Pageable pageable,String palabraClave);
}
