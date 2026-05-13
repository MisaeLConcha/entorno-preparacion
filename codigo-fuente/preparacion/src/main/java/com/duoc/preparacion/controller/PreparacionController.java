package com.duoc.preparacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.preparacion.model.Preparacion;
import com.duoc.preparacion.repository.PreparacionRepository;
import com.duoc.preparacion.service.PreparacionService;

@RestController
@RequestMapping("/api/v1/preparacion")
public class PreparacionController {

    @Autowired
    private PreparacionService service;
    @Autowired
    private PreparacionRepository repository;
    
    @GetMapping
    public List<Preparacion> listarPreparaciones() {
        return repository.findAll();
    }

    @PostMapping
    public Preparacion iniciar() {
        return service.iniciarPreparacion();
    }

    @PutMapping("/{id}/estado")
    public Preparacion actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return service.actualizarEstadoPreparacion(id, estado);
    }

    @PutMapping("/{id}/tiempo")
    public Preparacion establecerTiempo(@PathVariable Long id, @RequestParam int tiempo) {
        return service.establecerTiempoEstimado(id, tiempo);
    }

    @PutMapping("/{id}/lista")
    public Preparacion marcarLista(@PathVariable Long id) {
        return service.marcarPreparacionComoLista(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarPreparacion(@PathVariable Long id) {
        repository.deleteById(id);
        return "Preparacion eliminada correctamente";
}
}