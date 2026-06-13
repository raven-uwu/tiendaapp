package com.ufide.tiendaapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufide.tiendaapp.entity.Producto;
import com.ufide.tiendaapp.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repo.findByCategoria(categoria);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> bajoStock() {
        return repo.findByStockLessThan(5);
    }

    public Producto guardar(Producto p) {
        if (p.getPrecio() < 0)
            throw new IllegalArgumentException("Precio no puede ser negativo");
        if (p.getStock() < 0)
            throw new IllegalArgumentException("Stock no puede ser negativo");
        return repo.save(p);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public long total() {
        return repo.count();
    }
}
