package com.example.ProductFinder.servicio;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO);
    public Usuario guardarRol(Usuario usuario);
    public List<Usuario> listarUsuarios();

    public Usuario obtenerUsuarioPorId(Long id);
}
