package com.example.ProductFinder.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)//osea que no se a encontrado un archivo
public class FileNotFoundException extends RuntimeException{//Excepción de archivo no encontrado// esta clase es para que cuando se lanze una excepcion durante la ejecucion  devuelva un codigo de estado 404 y no 500 algo asi

    private static final long serialVersionUID = 1L;
    public FileNotFoundException(String mensaje){
        super(mensaje);
    }
    public FileNotFoundException(String mensaje, Throwable excepcion) {
        super(mensaje, excepcion);
    }

}
