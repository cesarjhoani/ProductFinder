package com.example.ProductFinder.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {
    @GetMapping("/login")// cuando ejecute la aplicacion lo primero es el login,/Spring Security reconocerá las solicitudes POST a "/login" pero creo que es al del SecurityConfiguration
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/")// cuando inicie sesion entra al index o por defecto tambien pasa
    public String verPaginaDeInicio(Model model) {
        String saludo = "";
        model.addAttribute("saludo",saludo);
        return "index";
    }
}
