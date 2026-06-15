package com.duoc.preparacion.controller;

import com.duoc.preparacion.dto.*;
import com.duoc.preparacion.service.PreparacionService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


// Imports de Swagger (agregar estos)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Preparacion", description = "Controlador de gestion de Preparacion de acuerdo a la necesidad")
@RestController
@RequestMapping("/api/v1/preparacion")
public class PreparacionController {

    @Autowired
    private PreparacionService service;

    //crear
    @Operation(
        summary = "Registrar nueva Preparacion",
        description = "Registra una nueva preparacion asociada a una orden"
    )
    @ApiResponse(responseCode = "201", description = "Preparacion creada exitosamente")
    @PostMapping
    public ResponseEntity<PreparacionDTO>
    crearPreparacion(
            @Valid
            @RequestBody
            PreparacionCreateDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearPreparacion(dto));
    }

    @Operation(summary = "Listar todas las Preparaciones",
               description = "Retorna la lista completa de Preparaciones registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<PreparacionDTO>>
    obtenerTodas() {
        return ResponseEntity.ok(
            service.obtenerTodas());
    }

    //GET x id
    @Operation(
        summary = "Buscar Preparacion por ID",
        description = "Obtiene la preparacion requerida de acuerdo al id ingresado previamente generado"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Preparacion encontrada"),
        @ApiResponse(responseCode = "404", description = "Preparacion no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PreparacionDTO>
    obtenerPorId(
        @Parameter(description = "ID único de la Preparacion", required = true)
        @PathVariable Long id) {

        return ResponseEntity.ok(
                service.obtenerPorId(id));
    }

    //actualizar
    @Operation(
        summary = "Actualizar Preparacion existente del estado",
        description = "Modifica el estado de la preparacion que se desee por medio el id de esta, mas el estado en la irl"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "Preparacion no encontrada")
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<PreparacionDTO>
    actualizarEstado(
            @Parameter(description = "ID de la Preparacion a actualizar")
            @PathVariable Long id,
            @RequestParam String estado) {

        return ResponseEntity.ok(
                service.actualizarEstado(id, estado));
    }

    //delete
    @Operation(
        summary = "Eliminar Preparacion",
        description = "Elimina la preparacion actual mediante el id de la misma")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eliminación exitosa"),
        @ApiResponse(responseCode = "404", description = "Preparacion no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminar(
        @Parameter(description = "ID de la Preparacion a eliminar")
        @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}