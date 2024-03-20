package com.example.ProductFinder.controlador;

import com.example.ProductFinder.servicio.AlmacenServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagenes")
public class AssetsController {

    @Autowired
    private AlmacenServicioImpl servicio;
    @GetMapping("/{filename}")//esta url recibe el valor de =${producto.rutaImagen} desde la vista
    public Resource obtenerRecurso(@PathVariable(value = "filename") String filenamee){
        return servicio.cargarComoRecurso(filenamee);// por lo que investigue me toco usar @RestController por que en este caso yo tenia que devolver un  recurso y no un  ModelAndView, String o View  Por lo tanto, @RestController es más adecuado en esta situación, ya que te permite devolver los recursos directamente sin necesidad de renderizar una vista HTML.
    }
}
