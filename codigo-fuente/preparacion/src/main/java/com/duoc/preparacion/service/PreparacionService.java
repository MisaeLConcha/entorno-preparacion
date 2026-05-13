package com.duoc.preparacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.preparacion.model.Preparacion;
import com.duoc.preparacion.repository.PreparacionRepository;

@Service
public class PreparacionService {

    @Autowired
    private PreparacionRepository repository;

    public Preparacion iniciarPreparacion() {
        Preparacion p = new Preparacion();
        p.setEstado("INICIADO");
        p.setTiempoEstimado(0);
        return repository.save(p);
    }

    public Preparacion actualizarEstadoPreparacion(Long id, String estado) {
        Preparacion p = repository.findById(id).orElseThrow();
        p.setEstado(estado);
        return repository.save(p);
    }

    public Preparacion establecerTiempoEstimado(Long id, int tiempo) {
        Preparacion p = repository.findById(id).orElseThrow();
        p.setTiempoEstimado(tiempo);
        return repository.save(p);
    }

    public Preparacion marcarPreparacionComoLista(Long id) {
        Preparacion p = repository.findById(id).orElseThrow();
        p.setEstado("LISTO");
        return repository.save(p);
    }
}