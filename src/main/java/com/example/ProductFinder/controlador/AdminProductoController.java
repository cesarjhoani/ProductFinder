package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.*;
import com.example.ProductFinder.repositorio.*;
import com.example.ProductFinder.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AlmacenServicioImpl servicio;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private SucursalesService sucursalesService;
    @Autowired
    private BodegaService bodegaService;
    @Autowired
    private ModuloService moduloService;
    @Autowired
    private PasilloService pasilloService;

    @GetMapping("/admin")
    public String adminProductos(@PageableDefault(sort = "nombre",size = 15)Pageable pageable, Model model){//paginacion por defecto en orden alfabetico por el nombre//recuerde verPaginaDeInicio
        Page<Producto> listaProductos = productoRepository.findAll(pageable);
        model.addAttribute("listaProductos",listaProductos);
        return "productos_admin";
    }
    @GetMapping("/agregarProducto")
    public String mostrarFormularioDeNuevoProducto(Model model){
        List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
        List<Categoria> listaCategorias = categoriaRepository.findAll(Sort.by("nombre"));
        List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
        List<Modulo>  listaModulos = moduloService.obtenerModulos();
        List<Pasillo>  listaPasillos = pasilloService.obtenerPasillos();
        model.addAttribute("listaSucursales",listaSucursales);
        model.addAttribute("listaCategorias",listaCategorias);
        model.addAttribute("listaBodegas",listaBodegas);
        model.addAttribute("listaModulos",listaModulos);
        model.addAttribute("listaPasillos",listaPasillos);
        model.addAttribute("producto",new Producto());
        return "registroProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute("producto")@Valid Producto producto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors() || producto.getImagen().isEmpty()){
            if(producto.getImagen().isEmpty()){//volvemos a preguntar para seguir forzando
                bindingResult.rejectValue("imagen","MultipartNotEmpty");
            }

            model.addAttribute("producto",producto);
            List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
            List<Categoria> listaCategorias = categoriaRepository.findAll(Sort.by("nombre"));
            List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
            List<Modulo>  listaModulos = moduloService.obtenerModulos();
            List<Pasillo>  listaPasillos = pasilloService.obtenerPasillos();
            model.addAttribute("listaSucursales",listaSucursales);
            model.addAttribute("listaCategorias",listaCategorias);
            model.addAttribute("listaBodegas",listaBodegas);
            model.addAttribute("listaModulos",listaModulos);
            model.addAttribute("listaPasillos",listaPasillos);
            return "registroProducto";
        }


            String rutaImagen = servicio.almacenarArchivo(producto.getImagen());// retorna el nombre de la imagen original
            producto.setRutaImagen(rutaImagen);

            productoService.guardar(producto);

        return "redirect:/admin";
    }
    //@GetMapping("/editar{id}")
    //@GetMapping("/producto/{id}/editar")
    @GetMapping("/editar{id}")
    public String MostrarFormularioEditarProducto(@PathVariable Integer id, Model model){
        Producto producto = productoService.obtenerProductoPorID(id);
        //Producto producto = productoRepository.getOne(id);

        model.addAttribute("producto",producto);
        List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
        List<Categoria> listaCategorias = categoriaRepository.findAll(Sort.by("nombre"));
        List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
        List<Modulo>  listaModulos = moduloService.obtenerModulos();
        List<Pasillo>  listaPasillos = pasilloService.obtenerPasillos();
        model.addAttribute("listaSucursales",listaSucursales);
        model.addAttribute("listaCategorias",listaCategorias);
        model.addAttribute("listaBodegas",listaBodegas);
        model.addAttribute("listaModulos",listaModulos);
        model.addAttribute("listaPasillos",listaPasillos);

        return "editarProducto";
    }




    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Integer id, RedirectAttributes flash){

        if(id>0){
            productoRepository.deleteById(id);
            flash.addFlashAttribute("success","Cliente eliminado con exito");
        }
        return "redirect:/admin";

    }



}
