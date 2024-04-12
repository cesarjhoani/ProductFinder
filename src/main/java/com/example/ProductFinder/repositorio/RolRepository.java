package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<Rol,Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre =:nombre")
    public Rol findByNombre(String nombre);
}
