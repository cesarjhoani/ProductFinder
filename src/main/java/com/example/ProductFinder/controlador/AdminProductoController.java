package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.Producto;
import com.example.ProductFinder.repositorio.*;
import com.example.ProductFinder.servicio.AlmacenServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AlmacenServicioImpl servicio;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private BodegaRepository bodegaRepository;
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private PasilloRepository pasilloRepository;

    @GetMapping("/admin")
    public String adminProductos(@PageableDefault(sort = "nombre",size = 5)Pageable pageable, Model model){//paginacion por defecto en orden alfabetico por el nombre//recuerde verPaginaDeInicio
        Page<Producto> listaProductos = productoRepository.findAll(pageable);
        model.addAttribute("listaProductos",listaProductos);
        return "productos_admin";
    }
    @GetMapping("/agregarProducto")
    public String mostrarFormularioDeNuevoProducto(){
        return "";
    }

}
