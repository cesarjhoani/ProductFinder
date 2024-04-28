package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Sucursales;
import com.example.ProductFinder.repositorio.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalesServiceImpl implements SucursalesService{
    @Autowired
    public SucursalRepository sucursalRepository;
    @Override
    public List<Sucursales> obtenerSucursales() {
        List<Sucursales> listaSucursales = sucursalRepository.findAll();
        return listaSucursales;
    }

    @Override
    public void guardarOeditar(Sucursales sucursales) {
        sucursalRepository.save(sucursales);
    }

    @Override
    public Optional<Sucursales> getSucursalById(Integer id) {
        return sucursalRepository.findById(id);
    }


}
