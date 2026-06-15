package com.duoc.preparacion.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreparacionTest {

    @Test
    void deberiaCrearPreparacionConConstructorVacio() {

        // Arrange
        Preparacion p = new Preparacion();

        // Assert
        assertNotNull(p);
    }

    @Test
    void deberiaSetearYObtenerValores() {

        // Arrange
        Preparacion p = new Preparacion();

        // Act
        p.setId(1L);
        p.setEstado("INICIADO");
        p.setTiempoEstimado(20);
        p.setIdOrden(3L);

        // Assert
        assertEquals(1L, p.getId());
        assertEquals("INICIADO", p.getEstado());
        assertEquals(20, p.getTiempoEstimado());
        assertEquals(3L, p.getIdOrden());
    }

    @Test
    void deberiaSerIgualOtraPreparacionConMismosDatos() {

        // Arrange
        Preparacion p1 = new Preparacion();
        p1.setId(1L);
        p1.setEstado("INICIADO");

        Preparacion p2 = new Preparacion();
        p2.setId(1L);
        p2.setEstado("INICIADO");

        // Assert
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}