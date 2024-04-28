package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Pasillo;
import com.example.ProductFinder.servicio.PasilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PasilloController {

    @Autowired
    public PasilloService pasilloService;
    @GetMapping("/pasillos")
    public String listarPasillos(Model model){
        List<Pasillo> Listapasillos = pasilloService.obtenerPasillos();
        model.addAttribute("Listapasillos",Listapasillos);
        model.addAttribute("titulo","Listado de pasillos");
        return "pasillos";
    }

    @PostMapping("/pasillos/guardarOeditar")
    public String guardarOeditarPasillo(@ModelAttribute("pasillo")Pasillo pasillo){
        pasilloService.guardar(pasillo);
        return "redirect:/pasillos";
    }

    @GetMapping("/pasillos/{id}")
    @ResponseBody
    public Pasillo getPasilloById(@PathVariable Integer id){
        Optional<Pasillo> optionalPasillo = pasilloService.getPasilloById(id);
        return optionalPasillo.orElse(null);
    }



}
