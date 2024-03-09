package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Sucursales;
import com.example.ProductFinder.servicio.SucursalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SucursalesController {
    @Autowired
    public SucursalesService sucursalesService;

    @GetMapping("/sucursales")
    public String listarSucursales(Model model){
        List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
        model.addAttribute("listaSucursales",listaSucursales);
        model.addAttribute("titulo","Listado de Sucursales");
        return "Sucursales";
    }
}
