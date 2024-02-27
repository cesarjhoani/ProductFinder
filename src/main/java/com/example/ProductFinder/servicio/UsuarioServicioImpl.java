package com.example.ProductFinder.servicio;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.modelo.Rol;
import com.example.ProductFinder.modelo.Usuario;
import com.example.ProductFinder.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Override
    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) {

        Usuario usuario =  new Usuario(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido(), usuarioRegistroDTO.getDocumento(),
                usuarioRegistroDTO.getSexo(),usuarioRegistroDTO.getTelefono(),usuarioRegistroDTO.getEmail(),usuarioRegistroDTO.getPassword(),
                Arrays.asList(new Rol("ROLE_USER")));

        return usuarioRepositorio.save(usuario);
    }
}
