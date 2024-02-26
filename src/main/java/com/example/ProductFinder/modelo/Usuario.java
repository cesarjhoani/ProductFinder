package com.example.ProductFinder.modelo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "documento")
    private String documento;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "telefono")
    private String telefono;

    private String email;
    private String password;
    private Collection<Rol> roles;


    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }
//genero un constructor de todos
    public Usuario(Long id, String nombre, String apellido, String documento, String sexo, String telefono, String email, String password, Collection<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    //genero un constructor de todos menos el id

    public Usuario(String nombre, String apellido, String documento, String sexo, String telefono, String email, String password, Collection<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    //y genero un constructor vacio


    public Usuario() {
    }
}

