package com.duoc.preparacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparacionCreateDTO {

    @NotNull(message = "El id de la orden es obligatorio")
    private Long idOrden;

    @NotNull(message = "El tiempo estimado es obligatorio")
    @Min(value = 1, message = "El tiempo debe ser mayor a 0")
    private Integer tiempoEstimado;
}