package com.ufide.tiendaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.tiendaapp.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

// Query methods derivados: Spring genera el SQL leyendo el nombre del método

List<Producto> findByCategoria(String categoria);
List<Producto> findByStockLessThan(int max);
List<Producto> findByNombreContainingIgnoreCase(String nombre);
}