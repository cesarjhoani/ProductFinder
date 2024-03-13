package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Pasillo;
import com.example.ProductFinder.repositorio.PasilloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasilloServiceImpl implements PasilloService{
    @Autowired
    public PasilloRepository pasilloRepository;
    @Override
    public List<Pasillo> obtenerPasillos() {
        List<Pasillo> listaPasillos = pasilloRepository.findAll();
        return listaPasillos;
    }
}
