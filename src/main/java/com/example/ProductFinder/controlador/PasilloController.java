package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Pasillo;
import com.example.ProductFinder.servicio.PasilloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}
