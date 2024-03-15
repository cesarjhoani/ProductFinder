package com.example.ProductFinder.servicio;

import com.example.ProductFinder.excepciones.AlmacenExcepcion;
import com.example.ProductFinder.excepciones.FileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AlmacenServicioImpl implements AlmacenServicio {

    @Value("${storage.location}")// el valor viene hacer assets
    private String storageLocation;//TRA ubicacion de almacenamiento

    @PostConstruct//sirve para indicar que este metodo se va a ejecutar cada vez que halla una nueva instancia de esta clase AlmacenServicioImpl
    @Override
    public void iniciarAlmacenDeArchivos() {

        try {
            Files.createDirectories(Paths.get(storageLocation));//obtenemos la ruta o assets para indicar donde almacenar nuestras fotos
        }catch (IOException exception){
            throw new AlmacenExcepcion("Error al inicializar la ubicación en el almacen de archivos");
        }

    }

    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if(archivo.isEmpty()){
            throw new AlmacenExcepcion("no se puede almacenar un archivo vacio");
        }try {
            InputStream inputStream = archivo.getInputStream();//obtengo los imputStream
            Files.copy(inputStream,Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);// si el archivo ya existe lo copia o  remplaza
        }catch (IOException exception){
            throw new AlmacenExcepcion("Error al almacenar el archivo " + nombreArchivo,exception);
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String nombreArchivo) {//retornamos el archivo,cargamos el arhcivo
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        try {
            Path archivo = cargarArchivo(nombreArchivo);// una vez tengamos el archivo
            Resource recurso =  new UrlResource(archivo.toUri());
            if(recurso.exists() || recurso.isReadable()){//si existe y es legible
                return recurso;
            }else {
                throw new FileNotFoundException("no se pudo encontrar el archivo"+nombreArchivo);
            }
        }catch (MalformedURLException exception){
            throw new FileNotFoundException("no se pudo encontrar el archivo"+nombreArchivo,exception);
        }
    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {
        Path archivo = cargarArchivo(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}
