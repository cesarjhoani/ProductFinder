package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Bodegas;
import com.example.ProductFinder.repositorio.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegasServiceImpl implements BodegaService{
    @Autowired
    public BodegaRepository bodegaRepository;
    @Override
    public List<Bodegas> obtenerBodegas() {
         List<Bodegas> listaBodegas = bodegaRepository.findAll();

        return listaBodegas;
    }

    @Override
    public void guardarOeditarBodega(Bodegas bodegas) {
        bodegaRepository.save(bodegas);
    }

    @Override
    public Optional<Bodegas> getBodegaById(Integer id) {
        return bodegaRepository.findById(id);
    }
}
