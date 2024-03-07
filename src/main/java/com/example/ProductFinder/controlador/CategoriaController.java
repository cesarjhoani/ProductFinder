package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Categoria;
import com.example.ProductFinder.repositorio.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public String listarCategorias(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAllByOrderByIdAsc();
        modelo.addAttribute("listaCategorias",listaCategorias);
        modelo.addAttribute("titulo","Listado de Categorias");
        return "categorias";
    }



    @GetMapping("/categorias/nuevo")
    public String mostrarFormularioDeNuevaCategoria(Model modelo){
        Categoria categoria = new Categoria();
        modelo.addAttribute("categoria",categoria);
        return "registroCategoria";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

}
