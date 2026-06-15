package com.duoc.preparacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    
    @Column(name = "tiempo_estimado")
    private Integer tiempoEstimado;

    @Column(name = "id_orden")
    private Long idOrden;
}