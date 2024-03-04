package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Categoria;
import com.example.ProductFinder.repositorio.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public String listarCategorias(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        modelo.addAttribute("listaCategorias",listaCategorias);
        modelo.addAttribute("titulo","Listado de Categorias");
        return "categorias";
    }


}
