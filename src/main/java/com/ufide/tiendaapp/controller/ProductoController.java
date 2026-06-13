package com.ufide.tiendaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ufide.tiendaapp.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listar(Model modelo,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String categoria) {
        if (buscar != null && !buscar.isBlank()) {
            modelo.addAttribute("productos", productoService.buscarPorNombre(buscar));
            modelo.addAttribute("filtro", "Buscando: " + buscar);
        } else if (categoria != null && !categoria.isBlank()) {
            modelo.addAttribute("productos", productoService.buscarPorCategoria(categoria));
            modelo.addAttribute("filtro", "Categoria: " + categoria);
        } else {
            modelo.addAttribute("productos", productoService.listar());
        }
        return "productos";
    }

    @GetMapping("/{id}")
    public String detalle(Model modelo, @PathVariable Long id) {
        modelo.addAttribute("producto", productoService.buscarPorId(id).orElse(null));
        return "producto";
    }

    @GetMapping("/bajo-stock")
    public String bajoStock(Model modelo) {
        modelo.addAttribute("productos", productoService.bajoStock());
        modelo.addAttribute("filtro", "Productos con bajo stock");
        return "productos";
    }
}