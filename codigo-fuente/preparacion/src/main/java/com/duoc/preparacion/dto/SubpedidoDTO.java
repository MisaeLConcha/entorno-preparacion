package com.duoc.preparacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubpedidoDTO {

    private Long id;
    private Long pedidoId;
    private Long standId;
    private String descripcion;
    private String estado;
}