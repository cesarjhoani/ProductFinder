package com.example.ProductFinder.controlador;

import com.example.ProductFinder.modelo.*;
import com.example.ProductFinder.repositorio.*;
import com.example.ProductFinder.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String listarProductos(@PageableDefault(sort = "nombre", size = 5) Pageable pageable, Model model, @Param("palabraClave") String palabraClave) {//paginacion por defecto en orden alfabetico por el nombre//recuerde verPaginaDeInicio
        //String palabraClave = "cesar";
        Page<Producto> listaProductos = productoService.obtenerListaProductos(pageable, palabraClave);
        model.addAttribute("listaProductos", listaProductos);
        return "productos_admin";
    }

    @GetMapping("/agregarProducto")
    public String mostrarFormularioDeNuevoProducto(Model model,Map<String,Object> modelo) {
        List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
        List<Categoria> listaCategorias = categoriaRepository.findAll(Sort.by("nombre"));
        List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
        List<Modulo> listaModulos = moduloService.obtenerModulos();
        List<Pasillo> listaPasillos = pasilloService.obtenerPasillos();
        model.addAttribute("listaSucursales", listaSucursales);
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("listaBodegas", listaBodegas);
        model.addAttribute("listaModulos", listaModulos);
        model.addAttribute("listaPasillos", listaPasillos);
        model.addAttribute("producto", new Producto());
        modelo.put("titulo","Registrar Producto");
        return "registrarOeditarProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute("producto") @Valid Producto producto, BindingResult bindingResult, Model model, HttpServletRequest request) {
                // primero valida si los formularios estan completos
        if (bindingResult.hasErrors() || producto.getImagen().isEmpty()) {
            if (producto.getImagen().isEmpty()) {
                bindingResult.rejectValue("imagen", "MultipartNotEmpty");
            }

            model.addAttribute("producto", producto);
            List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
            List<Categoria> listaCategorias = categoriaRepository.findAll(Sort.by("nombre"));
            List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
            List<Modulo> listaModulos = moduloService.obtenerModulos();
            List<Pasillo> listaPasillos = pasilloService.obtenerPasillos();
            model.addAttribute("listaSucursales", listaSucursales);
            model.addAttribute("listaCategorias", listaCategorias);
            model.addAttribute("listaBodegas", listaBodegas);
            model.addAttribute("listaModulos", listaModulos);
            model.addAttribute("listaPasillos", listaPasillos);
            return "registrarOeditarProducto";
        }

        // Manejo de detalles
        String[] detalleId = request.getParameterValues("detalleId");
        String[] detallesNombre = request.getParameterValues("detallesNombre");
        String[] detallesValor = request.getParameterValues("detallesValor");


        for(int i=0; i<detallesNombre.length; i++) {//si devuelve lleno detallesNombre  desde el formulario edito
            if (detalleId != null && detalleId.length > 0) {//para editar si ya existen detalles en el producto traido
                producto.editarDetalles(Integer.valueOf(detalleId[i]), detallesNombre[i], detallesValor[i]);
            } else if (detallesNombre[i].isEmpty() && detallesValor[i].isEmpty()) {//para registrar producto sin detalles
                productoService.guardar(producto);
                return "redirect:/admin";
            } else {// o para registrar producto con detalles
                producto.añadirDetalles(detallesNombre[i], detallesValor[i]);
            }
        }

        String rutaImagen = servicio.almacenarArchivo(producto.getImagen());// retorna el nombre de la imagen original
        producto.setRutaImagen(rutaImagen);

        productoService.guardar(producto);

        return "redirect:/admin";
    }

    //@GetMapping("/editar{id}")
    //@GetMapping("/producto/{id}/editar")
    @GetMapping("/editar{id}")
    public String MostrarFormularioEditarProducto(@PathVariable Integer id, Model model, Map<String,Object> modelo) {
        Producto producto = productoService.obtenerProductoPorID(id);
        //Producto producto = productoRepository.getOne(id);

        model.addAttribute("producto", producto);
        List<Sucursales> listaSucursales = sucursalesService.obtenerSucursales();
        List<Categoria> listaCategorias = categoriaRepository.findAll(Sort.by("nombre"));
        List<Bodegas> listaBodegas = bodegaService.obtenerBodegas();
        List<Modulo> listaModulos = moduloService.obtenerModulos();
        List<Pasillo> listaPasillos = pasilloService.obtenerPasillos();
        model.addAttribute("listaSucursales", listaSucursales);
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("listaBodegas", listaBodegas);
        model.addAttribute("listaModulos", listaModulos);
        model.addAttribute("listaPasillos", listaPasillos);
        modelo.put("titulo","Editar Registro");
        return "registrarOeditarProducto";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {

        if (id > 0) {
            //Producto producto = productoRepository.getOne(id);
            productoRepository.deleteById(id);
            //servicio.eliminarArchivo(producto.getRutaImagen());  desactivo el metodo para que las imagenes que tengan otras sucursales no sean afectadas
            flash.addFlashAttribute("success", "Producto eliminado con exito");
        }
        return "redirect:/admin";

    }


}
