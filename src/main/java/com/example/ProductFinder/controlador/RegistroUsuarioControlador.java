package com.example.ProductFinder.controlador;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.modelo.Rol;
import com.example.ProductFinder.modelo.Usuario;
import com.example.ProductFinder.repositorio.RolRepository;
import com.example.ProductFinder.repositorio.UsuarioRepositorio;
import com.example.ProductFinder.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    RolRepository rolRepository;

    @GetMapping
    public String mostrarFormularioDeRegistro(Model model){
    model.addAttribute("usuario",new UsuarioRegistroDTO());
    return "registro";
    }
    @GetMapping("/editarRol/{id}")
    public String mostrarFormularioEditarRoles(@PathVariable Long id,Model model){
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        Collection<Rol> listaRoles = rolRepository.findAll();
        model.addAttribute("usuario",usuario);
        model.addAttribute("listaRoles",listaRoles);
        return "editarRol";
    }
    @PostMapping("/roles/guardar")
    public String editarRoles(@ModelAttribute("usuario") Usuario usuario){
        usuarioServicio.guardarRol(usuario);
        return "redirect:/registro/listarUsuarios";
    }


    @GetMapping("/listarUsuarios")
    public String listarUsuarios(Model model){

        List<Usuario> listaUsuarios = usuarioServicio.listarUsuarios();

        for(Usuario usuario:listaUsuarios){
            for(Rol rol:usuario.getRoles()){
                if("ROLE_ADMIN".equals(rol.getNombre())){
                    rol.setNombre("ADMINISTRADOR");
                }else if("ROLE_USER".equals(rol.getNombre())){
                    rol.setNombre("USUARIO NORMAL");
                }
            }
        }

        model.addAttribute("listaUsuarios",listaUsuarios);
        return "listaUsuarios";

    }


    @PostMapping
    public String guardarUsuario(@ModelAttribute("usuario") @Valid UsuarioRegistroDTO registroDTO, BindingResult result){

        if (result.hasErrors()) {
            // Si hay errores de validación, regresa a la página de registro y muestre los mensahes atravez de la vista
            return "registro";
        }

    usuarioServicio.guardar(registroDTO);
    return "redirect:/registro?exito";// se redirije a /registro o url de las solicitudes de este mismo controlador
    }
}
