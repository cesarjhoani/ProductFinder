package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Modulo;
import com.example.ProductFinder.servicio.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ModuloController {

    @Autowired
    public ModuloService moduloService;


    //para listar los modulos
    @GetMapping("/modulos")
    public String ListarModulos(Model model){
        List<Modulo> ListaModulos = moduloService.obtenerModulos();
        model.addAttribute("ListaModulos",ListaModulos);
        return "modulos";
    }

    //para guardar o editar un modulo traido desde un modal
    @PostMapping("/modulos/guardarOeditar")
    public String guardarOeditarModulo(@ModelAttribute("modulo") Modulo modulo){
        moduloService.guardarModulo(modulo);
        return "redirect:/modulos";
    }

    @GetMapping("/modulos/{id}")
    @ResponseBody
    public Modulo getModuloById(@PathVariable Integer id){
        Optional<Modulo> optionalModulo = moduloService.getModuloById(id);
        return optionalModulo.orElse(null);
    }
}
