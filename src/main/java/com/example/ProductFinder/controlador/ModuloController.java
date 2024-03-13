package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Modulo;
import com.example.ProductFinder.servicio.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ModuloController {

    @Autowired
    public ModuloService moduloService;

    @GetMapping("/modulos")
    public String ListarModulos(Model model){
        List<Modulo> ListaModulos = moduloService.obtenerModulos();
        model.addAttribute("ListaModulos",ListaModulos);
        return "modulos";
    }
}
