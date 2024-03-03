package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    public Usuario findByEmail(String email);// metodo propio de Spring Data jpa
}
