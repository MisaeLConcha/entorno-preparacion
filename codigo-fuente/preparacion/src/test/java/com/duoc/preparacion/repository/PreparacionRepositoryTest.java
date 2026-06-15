package com.duoc.preparacion.repository;

import com.duoc.preparacion.model.Preparacion;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
class PreparacionRepositoryTest {

    @Autowired
    private PreparacionRepository repository;

    @Test
    void deberiaGuardarPreparacion() {

        Preparacion p =
                new Preparacion();

        p.setEstado("INICIADO");
        p.setTiempoEstimado(20);

        Preparacion guardado =
                repository.save(p);

        assertNotNull(
                guardado.getId());
    }

    @Test
    void deberiaBuscarPreparacionPorId() {

        Preparacion p =
                new Preparacion();

        p.setEstado("LISTO");

        p = repository.save(p);

        Optional<Preparacion> resultado =
                repository.findById(
                        p.getId());

        assertTrue(
                resultado.isPresent());
    }

    @Test
    void deberiaEliminarPreparacion() {

        Preparacion p =
                new Preparacion();

        p.setEstado("LISTO");

        p = repository.save(p);

        repository.deleteById(
                p.getId());

        assertFalse(
                repository.findById(
                        p.getId())
                        .isPresent());
    }
}