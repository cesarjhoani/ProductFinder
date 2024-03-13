package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Modulo;
import com.example.ProductFinder.repositorio.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloServiceImpl implements ModuloService{
    @Autowired
    public ModuloRepository moduloRepository;


    @Override
    public List<Modulo> obtenerModulos() {
        List<Modulo> listaModulos =  moduloRepository.findAll();
        return listaModulos;

    }
}
