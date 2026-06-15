package com.duoc.preparacion.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PpordenDTO {

    private Long id;
    private String norden;
    private String tipo;
    private String estado;
    private LocalDateTime fechaCreacion;
    private Long eventoId;
    private Long usuarioId;

    private List<PedidoItemDTO> items;

}