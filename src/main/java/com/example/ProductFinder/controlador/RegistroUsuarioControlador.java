package com.example.ProductFinder.controlador;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping
    public String mostrarFormularioDeRegistro(Model model){
    model.addAttribute("usuario",new UsuarioRegistroDTO());
    return "registro";
    }

    @PostMapping
    public String guardarUsuario(@ModelAttribute("usuario") @Valid UsuarioRegistroDTO registroDTO, BindingResult result){
/*
        if (result.hasErrors()) {
            // Si hay errores de validación, regresa a la página de registro y muestre los mensahes atravez de la vista
            return "registro";
        }*/

    usuarioServicio.guardar(registroDTO);
    return "redirect:/registro?exito";// se redirije a /registro o url de las solicitudes de este mismo controlador
    }
}
