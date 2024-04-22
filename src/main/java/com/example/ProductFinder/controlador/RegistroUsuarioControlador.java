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

    //para que el administrador pueda  modificarle un rol a un usuario
    @GetMapping("/editarRol/{id}")
    public String mostrarFormularioEditarRolesAusuarios(@PathVariable Long id,Model model){
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        Collection<Rol> listaRoles = rolRepository.findAll();
        //para que en el selector o combo box de editar rol de usuario se vea los cargos con nombres mas entendibles para el usuario
        // si en un futuro queremos mas roles pondre mejor un switch
        for(Rol rol: listaRoles){
            if("ROLE_ADMIN".equals(rol.getNombre())){
                rol.setNombre("Administrador");
            }else if ("ROLE_USER".equals(rol.getNombre())){
                rol.setNombre("Usuario Normal");
            }else {
                rol.setNombre(rol.getNombre());
            }

        }
        model.addAttribute("usuario",usuario);
        model.addAttribute("listaRoles",listaRoles);
        return "editarRol";
    }
    //edito el rol del usuario sin alterar su contraseña
    @PostMapping("/roles/guardar")
    public String editarRoles(@ModelAttribute("usuario") Usuario usuario){
        Usuario usuarioOriginal = usuarioServicio.obtenerUsuarioPorId(usuario.getId());
        usuario.setPassword(usuarioOriginal.getPassword());
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
