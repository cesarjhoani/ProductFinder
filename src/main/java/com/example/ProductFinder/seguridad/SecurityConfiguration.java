package com.example.ProductFinder.seguridad;

import com.example.ProductFinder.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    private UsuarioServicio  usuarioServicio;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ // indico como codifico la contraseña por que spring security espera una pasword encriptada
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){//provedor de autenticacion para autenticar los usuarios en una aplicación web mediante la autenticación basada en bases de datos.
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);//le establesco el UserDetailsService a UsuarioServicio con extends para extraer los datos de un usuario y voy a tener sus datos roles, etc por que tiene el metodo loadUserByUsername() me carga un usuario
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {// es lo que va a validar si los datos que estoy obteniendo son validos o no
        auth.authenticationProvider(authenticationProvider());
    }

    // se configura un éxito de inicio de sesión personalizado utilizando la interfax AuthenticationSuccessHandler.
    @Component
    public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
            response.sendRedirect("/");
        }
    }

    @Override //sobrescribo el metodo configure que extiende WebSecurityConfigurerAdapter, la cual es una clase de utilidad para saber como deseamos configurar la seguridad
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/registro**",
                        "/error**"
                ).permitAll()
                .antMatchers("/static/**").denyAll() // Denegar acceso a la carpeta static y su contenido
                //.antMatchers("/hola**").hasRole("ADMIN")
                //.antMatchers("/hola").hasAuthority("OTROROL")  POR si quiere autorizar a un rol en especifico sin el porefijo ROLE_
                //.antMatchers("/admin","/publico").hasRole("ADMIN")
                .anyRequest().authenticated()// cualquier otra peticion vamos a autenticarla
                .and()
                .formLogin()
                .loginPage("/login")
                //.defaultSuccessUrl("/modulos")  //por si quiero que se redirija a una pagina por defecto despues de que inice sesion
                .permitAll()
                .successHandler(new CustomAuthenticationSuccessHandler()) // Use el manejador de éxito personalizado
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}
