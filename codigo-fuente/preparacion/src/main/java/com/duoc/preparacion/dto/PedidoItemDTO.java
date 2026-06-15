package com.duoc.preparacion.dto;

import lombok.Data;

@Data
public class PedidoItemDTO {

    private Long id;
    private String nombreProducto;
    private int cantidad;
    private double precio;

}