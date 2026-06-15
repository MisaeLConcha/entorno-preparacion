package com.duoc.preparacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparacionDTO {

    private Long id;
    private String estado;
    private Integer tiempoEstimado;
    private Long idOrden;
}