package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Pasillo;

import java.util.List;
import java.util.Optional;

public interface PasilloService {
    public List<Pasillo> obtenerPasillos();

    public void guardar(Pasillo pasillo);
    public Optional<Pasillo> getPasilloById(Integer id);
}
