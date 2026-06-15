package com.duoc.preparacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    description = "Datos necesarios para crear una preparación."
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparacionCreateDTO {

    @Schema(
        description = "ID de la orden asociada",
        example = "1"
    )
    @NotNull(message = "El id de la orden es obligatorio")
    private Long idOrden;

    @Schema(
        description = "Tiempo estimado de preparación en minutos",
        example = "20"
    )
    @NotNull(message = "El tiempo estimado es obligatorio")
    @Min(value = 1, message = "El tiempo debe ser mayor a 0")
    private Integer tiempoEstimado;
}