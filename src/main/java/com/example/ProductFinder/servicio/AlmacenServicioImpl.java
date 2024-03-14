package com.example.ProductFinder.servicio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@Service
public class AlmacenServicioImpl implements AlmacenServicio {

    @Value("${storage.location}")// el valor viene hacer assets
    private String storageLocation;//TRA ubicacion de almacenamiento

    @PostConstruct//sirve para indicar que este metodo se va a ejecutar cada vez que halla una nueva instancia de esta clase AlmacenServicioImpl
    @Override
    public void iniciarAlmacenDeArchivos() {

    }

    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        return null;
    }

    @Override
    public Path cargarArchivo(String nombreArchivo) {
        return null;
    }

    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        return null;
    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {

    }
}
