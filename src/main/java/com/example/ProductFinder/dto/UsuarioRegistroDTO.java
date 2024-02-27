package com.example.ProductFinder.dto;

public class UsuarioRegistroDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String documento;
    private String sexo;
    private String telefono;
    private String email;
    private String password;


    //getters and setters

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

    // constructor lleno


    public UsuarioRegistroDTO(Long id, String nombre, String apellido, String documento, String sexo, String telefono, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }
    // constructor lleno menos el id

    public UsuarioRegistroDTO(String nombre, String apellido, String documento, String sexo, String telefono, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    // constructor solo con el email


    public UsuarioRegistroDTO(String email) {
        this.email = email;
    }

    // constructor vacio


    public UsuarioRegistroDTO() {
    }
}
