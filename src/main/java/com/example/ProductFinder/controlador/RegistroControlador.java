package com.example.ProductFinder.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {
    @GetMapping("/login")// cuando ejecute la aplicacion lo primero es el login
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/")// SIEMPRE QUE ENTRE EN VACIO VENIR AL INDEX
    public String verPaginaDeInicio() {

        return "index";
    }
}
