package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Categoria;
import com.example.ProductFinder.repositorio.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // para listar las categorias
    @GetMapping("/categorias")
    public String listarCategorias(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAllByOrderByIdAsc();
        modelo.addAttribute("listaCategorias",listaCategorias);
        modelo.addAttribute("titulo","Listado de Categorias");
        return "categorias";
    }


    // para actualizar una categoría
    @PostMapping("/categorias/actualizar")
    public String actualizarCategoria(@ModelAttribute("categoria") Categoria categoria){
        // Aquí puedes agregar validaciones o procesamiento adicional antes de guardar
        categoriaRepository.save(categoria);
        return "redirect:/categorias?edicion"; // Redirige a la lista de categorías después de la actualización
    }

    // para guardar una categoria traida desde un modal
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categorias?exito";
    }

    //para antes de actualizar enviar el objecto llamado por una peticion ajax al modal
    @GetMapping("/categorias/{id}")
    @ResponseBody
    public Categoria getCategoriaById(@PathVariable Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElse(null);
    }

}
