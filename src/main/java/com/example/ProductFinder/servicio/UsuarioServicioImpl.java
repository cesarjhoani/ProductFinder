package com.example.ProductFinder.servicio;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.modelo.Rol;
import com.example.ProductFinder.modelo.Usuario;
import com.example.ProductFinder.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) {

        Usuario usuario = new Usuario(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido(), usuarioRegistroDTO.getDocumento(),
                usuarioRegistroDTO.getSexo(), usuarioRegistroDTO.getTelefono(), usuarioRegistroDTO.getEmail(), passwordEncoder.encode(usuarioRegistroDTO.getPassword()),
                Arrays.asList(new Rol("ROLE_USER")));

        return usuarioRepositorio.save(usuario);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username);// el username es el email
        if (usuario == null) {
            throw new UsernameNotFoundException("usuarios o contraseña incorrectos");
        }
        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));// importante importar este sobre User import org.springframework.security.core.userdetails.User;
    }


    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {//por cada rol obtengo su rol y lo mapeo
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
}
