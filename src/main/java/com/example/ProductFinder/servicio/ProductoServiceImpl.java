package com.example.ProductFinder.servicio;

import com.example.ProductFinder.modelo.Producto;
import com.example.ProductFinder.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProductoPorID(Integer id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Page<Producto> obtenerListaProductos(Pageable pageable,String palabraClave) {
        if(palabraClave !=null){
            Page<Producto> productosFiltrados = productoRepository.filtrarProducto(pageable,palabraClave);
            return productosFiltrados;
        }
        Page<Producto> listaProductos = productoRepository.findAll(pageable);
        return listaProductos;
    }

    @Override
    public List<Producto> obtenerUltimosProductos() {// obtengo los ultimos 5 productos en orden alfabetico y en forma desendente
        List<Producto> ultimasPeliculas = productoRepository.findAll(PageRequest.of(0,8, Sort.by("fechaRegistro").descending())).toList();
        return ultimasPeliculas;
    }




}
