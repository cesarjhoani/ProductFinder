package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<Rol,Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre =:nombre")
    public Rol findByNombre(String nombre);
    /*

    // Consulta nativa
    @Query(value = "SELECT * FROM Rol WHERE nombre = :nombre", nativeQuery = true)
    public Rol findByNombre(@Param("nombre") String nombre);

     */
}
