package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Pasillo;
import com.example.ProductFinder.repositorio.PasilloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasilloServiceImpl implements PasilloService{
    @Autowired
    public PasilloRepository pasilloRepository;
    @Override
    public List<Pasillo> obtenerPasillos() {
        List<Pasillo> listaPasillos = pasilloRepository.findAll();
        return listaPasillos;
    }

    @Override
    public void guardar(Pasillo pasillo) {
        pasilloRepository.save(pasillo);
    }

    @Override
    public Optional<Pasillo> getPasilloById(Integer id) {
        return pasilloRepository.findById(id);
    }
}
