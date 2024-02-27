package com.example.ProductFinder.servicio;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.modelo.Usuario;

public interface UsuarioServicio {

    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO);
}
