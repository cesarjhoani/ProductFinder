package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Bodegas;
import com.example.ProductFinder.servicio.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BodegasController {

    @Autowired
    public BodegaService bodegaService;

    @GetMapping("/bodegas")
    public String ListarBodegas(Model model){
        List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
        model.addAttribute("listaBodegas",listaBodegas);
        model.addAttribute("titulo","Listado de Bodegas");
        return "bodegas";
    }
}
