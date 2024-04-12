package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Producto;
import com.example.ProductFinder.repositorio.ProductoRepository;
import com.example.ProductFinder.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoService productoService;
    @GetMapping("/login")// cuando ejecute la aplicacion lo primero es el login,/Spring Security reconocerá las solicitudes POST a "/login" pero creo que es al del SecurityConfiguration
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/")// cuando inicie sesion entra al index o por defecto tambien pasa
    public String verPaginaDeInicio(Model model) {
        List<Producto> ultimasProductos = productoService.obtenerUltimosProductos();
        model.addAttribute("ultimasProductos",ultimasProductos);
        return "index";
    }
}
