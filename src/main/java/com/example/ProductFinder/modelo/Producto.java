package com.example.ProductFinder.modelo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String codigoBarras;
    private String rutaImagen;

    @Transient// no sera persistente en la base de datos
    private MultipartFile imagen; //para manejar la carga de archivos, como imágenes, documentos, etc.va hacer temporal

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaRegistro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sucursal_id") // como no puse una estrategia de recuperación entonces la predeterminada es eager (ansiosa).
    private Sucursales sucursales;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private Bodegas bodegas;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pasillo_id")
    private Pasillo pasillo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @NotEmpty
    private String nivel;

    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL)
    private List<ProductoDetalles> detalles = new ArrayList<>();//vamos añadir detalles a este array

    public Producto() {
    }

    public Producto(String nombre, String codigoBarras, String rutaImagen, MultipartFile imagen, LocalDate fechaRegistro, Sucursales sucursales, Categoria categoria, Bodegas bodegas, Pasillo pasillo, Modulo modulo, String nivel) {
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.rutaImagen = rutaImagen;
        this.imagen = imagen;
        this.fechaRegistro = fechaRegistro;
        this.sucursales = sucursales;
        this.categoria = categoria;
        this.bodegas = bodegas;
        this.pasillo = pasillo;
        this.modulo = modulo;
        this.nivel = nivel;
    }

    public Producto(Integer id, String nombre, String codigoBarras, String rutaImagen, MultipartFile imagen, LocalDate fechaRegistro, Sucursales sucursales, Categoria categoria, Bodegas bodegas, Pasillo pasillo, Modulo modulo, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.rutaImagen = rutaImagen;
        this.imagen = imagen;
        this.fechaRegistro = fechaRegistro;
        this.sucursales = sucursales;
        this.categoria = categoria;
        this.bodegas = bodegas;
        this.pasillo = pasillo;
        this.modulo = modulo;
        this.nivel = nivel;
    }
// para añadir los detalles a un producto si el cliente quiere
    public void añadirDetalles(String nombre,String valor){
        this.detalles.add(new ProductoDetalles(nombre,valor,this));// el this hace referencia al producto actual
    }
// para editar los detalles de un producto, si el producto ya a tenido detalles
    public void editarDetalles(Integer id,String nombre,String valor){
        this.detalles.add(new ProductoDetalles(id,nombre,valor,this));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Bodegas getBodegas() {
        return bodegas;
    }

    public void setBodegas(Bodegas bodegas) {
        this.bodegas = bodegas;
    }

    public Pasillo getPasillo() {
        return pasillo;
    }

    public void setPasillo(Pasillo pasillo) {
        this.pasillo = pasillo;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<ProductoDetalles> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ProductoDetalles> detalles) {
        this.detalles = detalles;
    }
}
