package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Bodegas;
import com.example.ProductFinder.modelo.Modulo;
import com.example.ProductFinder.servicio.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BodegasController {

    @Autowired
    public BodegaService bodegaService;

    @GetMapping("/bodegas")
    public String ListarBodegas(Model model){
        List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
        model.addAttribute("listaBodegas",listaBodegas);
        model.addAttribute("titulo","Listado de Secciones");
        return "bodegas";
    }

    @PostMapping("/bodegas/guardarOeditar")
    public String guardarOeditarModulo(@ModelAttribute Bodegas bodegas){
        bodegaService.guardarOeditarBodega(bodegas);
        return "redirect:/bodegas";
    }

    @GetMapping("/bodegas/{id}")
    @ResponseBody
    public Bodegas getBodegaById(@PathVariable Integer id){
        Optional<Bodegas> optionalBodegas = bodegaService.getBodegaById(id);
        return optionalBodegas.orElse(null);
    }
}
