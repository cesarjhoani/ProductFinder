package com.example.ProductFinder.servicio;
import com.example.ProductFinder.modelo.Sucursales;
import java.util.List;
import java.util.Optional;

public interface SucursalesService {
    public List<Sucursales> obtenerSucursales();
    public void guardarOeditar(Sucursales sucursales);

    public Optional<Sucursales> getSucursalById(Integer id);
}
