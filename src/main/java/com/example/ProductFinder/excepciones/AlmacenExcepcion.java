package com.example.ProductFinder.excepciones;
public class AlmacenExcepcion extends RuntimeException{//Excepción de archivo no encontrado// esta clase es para que cuando se lanze una excepcion durante la ejecucion  devuelva un codigo de estado 404 y no 500 algo asi

    private static final long serialVersionUID = 1L;
    public AlmacenExcepcion(String mensaje){ //CONSTRUCTOR personalizado
        super(mensaje);
    }
    public AlmacenExcepcion(String mensaje, Throwable excepcion) {
        super(mensaje, excepcion);
    }//CONSTRUCTOR personalizado

}
