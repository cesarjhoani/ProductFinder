package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Bodegas;

import java.util.List;
import java.util.Optional;

public interface BodegaService {
    public List<Bodegas> obtenerBodegas();
    public void guardarOeditarBodega(Bodegas bodegas);
    public Optional<Bodegas> getBodegaById(Integer id);
}
