package com.example.ProductFinder.repositorio;

import com.example.ProductFinder.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    List<Categoria> findAllByOrderByIdAsc();// para que me liste en orden ya que aun no se por que se me viene en desorden los id
}
