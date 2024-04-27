package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Modulo;

import java.util.List;
import java.util.Optional;

public interface ModuloService {

    public List<Modulo> obtenerModulos();
    public void guardarModulo(Modulo modulo);

    public Optional<Modulo> getModuloById(Integer id);
}
