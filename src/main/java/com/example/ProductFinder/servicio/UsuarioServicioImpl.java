package com.example.ProductFinder.servicio;

import com.example.ProductFinder.dto.UsuarioRegistroDTO;
import com.example.ProductFinder.modelo.Rol;
import com.example.ProductFinder.modelo.Usuario;
import com.example.ProductFinder.repositorio.RolRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) {
            // Buscamos los roles coorespondientes si existen
        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN");
        Rol rolUser = rolRepository.findByNombre("ROLE_USER");

        // Si el rol "ROLE_ADMIN" no existe, lo creamos
        if (rolAdmin == null) {
            rolAdmin = new Rol("ROLE_ADMIN");
            rolRepository.save(rolAdmin);
        }

        // Si no existe el rol "ROLE_USER", lo creamos
        if (rolUser == null) {
            rolUser = new Rol("ROLE_USER");
            rolRepository.save(rolUser);
        }

        Rol rolUsuario;

        // Verificamos si no hay usuarios registrados contando cuantos usuarios hay, si hay 2 o mas los demas seran ROLE_USER
        if (usuarioRepositorio.count() <2 ) {
             rolUsuario = rolAdmin;
        } else {
            rolUsuario = rolUser;
        }

        // Creamos el usuario con el rol correspondiente
        Usuario usuario = new Usuario(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido(), usuarioRegistroDTO.getDocumento(),
                usuarioRegistroDTO.getSexo(), usuarioRegistroDTO.getTelefono(), usuarioRegistroDTO.getEmail(), passwordEncoder.encode(usuarioRegistroDTO.getPassword()),
                Arrays.asList(rolUsuario));

        return usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // este metodo lo utiliza authenticationProvider() de la clase SecurityConfiguration  ya que UsuarioServicio extiende UserDetailsService y como la clase UsuarioServicioImpl implementa UsuarioServicio entonces debemos agregar aqui este mismo metodo loadUserByUsername(String username)que hace la autenticacion se valida con el email y password
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }

        StringBuilder userDetailsBuilder = new StringBuilder();
        userDetailsBuilder.append(usuario.getEmail()).append(" ").append("<br>"+"Tu rol es ");

        StringBuilder rolesBuilder = new StringBuilder();
        for (Rol rol : usuario.getRoles()) {
            String roleName = rol.getNombre();
            if ("ROLE_USER".equals(roleName)) {
                roleName = "<span style=\"color: blue;\">Cliente</span>";
            } else if ("ROLE_ADMIN".equals(roleName)) {
                roleName = roleName = "<span style=\"color: #3a5f6d;\"><strong>Administrador</strong></span>";
            }
            rolesBuilder.append(roleName);
        }
        String rolesString = rolesBuilder.toString();

        userDetailsBuilder.append(rolesString);

        return new User(userDetailsBuilder.toString(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {//por cada rol obtengo su rol y lo mapeo
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
}
