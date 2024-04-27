package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Modulo;
import com.example.ProductFinder.repositorio.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImpl implements ModuloService{
    @Autowired
    public ModuloRepository moduloRepository;


    @Override
    public List<Modulo> obtenerModulos() {
        List<Modulo> listaModulos =  moduloRepository.findAll();
        return listaModulos;

    }

    @Override
    public void guardarModulo(Modulo modulo) {
        moduloRepository.save(modulo);
    }

    @Override
    public Optional<Modulo> getModuloById(Integer id) {
       return moduloRepository.findById(id);
    }
}
