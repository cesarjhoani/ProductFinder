package com.example.ProductFinder.servicio;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface AlmacenServicio {
    public void iniciarAlmacenDeArchivos();

    public String almacenarArchivo(MultipartFile archivo);

    public Path cargarArchivo(String nombreArchivo);

    public Resource cargarComoRecurso(String nombreArchivo);// importante el resource pero el import de arriba toco manual

    public void eliminarArchivo(String nombreArchivo);

}
