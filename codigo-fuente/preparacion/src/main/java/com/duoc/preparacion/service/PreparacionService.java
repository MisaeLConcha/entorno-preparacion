package com.duoc.preparacion.service;

import com.duoc.preparacion.client.NotificacionClient;
import com.duoc.preparacion.client.SubpedidoClient;
import com.duoc.preparacion.dto.*;
import com.duoc.preparacion.exception.BadRequestException;
import com.duoc.preparacion.exception.ResourceNotFoundException;
import com.duoc.preparacion.model.Preparacion;
import com.duoc.preparacion.repository.PreparacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PreparacionService {

    @Autowired
    private PreparacionRepository repository;

    @Autowired
    private SubpedidoClient subpedidoClient;

    @Autowired
    private NotificacionClient notificacionClient;

    private static final Logger log =
        LoggerFactory.getLogger(
                 PreparacionService.class);

    // CREAR
    public PreparacionDTO crearPreparacion(
        PreparacionCreateDTO dto) {
        log.info(
            "Iniciando creacion de preparacion para orden {}",
            dto.getIdOrden());
        //para contadores sobre 0
        if (dto.getTiempoEstimado() <= 0) {
        log.warn(
                "Tiempo estimado invalido: {}",dto.getTiempoEstimado());
        throw new BadRequestException(
                "El tiempo estimado debe ser mayor a 0");
    }

        try {
        // GET a subpedido
            SubpedidoDTO subpedido =
                subpedidoClient.getSubpedidoById(
                        dto.getIdOrden());

            if (subpedido == null) {
                log.warn(
                    "No se encontro subpedido para la orden {}",
                    dto.getIdOrden());
                throw new ResourceNotFoundException(
                    "Subpedido no encontrado");
        }

        // crear entidad
        Preparacion preparacion =
                new Preparacion();

        preparacion.setEstado("INICIADO");
        preparacion.setTiempoEstimado(
                dto.getTiempoEstimado());

        preparacion.setIdOrden(
                dto.getIdOrden());

        // guardar
        Preparacion guardado =
                repository.save(preparacion);

        log.info(
                "Preparacion {} creada correctamente",
                guardado.getId());

        // POST notificacion
        NotificacionDTO noti =
                new NotificacionDTO();

        noti.setMensaje(
                "Preparacion iniciada para orden "
                        + dto.getIdOrden());

        noti.setEstado("PENDIENTE");

        try {
            notificacionClient
                    .crearNotificacion(noti);
            log.info(
                    "Notificacion enviada para orden {}",
                    dto.getIdOrden());

        } catch (Exception e) {
                        log.error(
                    "No se pudo enviar notificación para orden {}",
                    dto.getIdOrden(),
                    e);
        }

        // retornar DTO
        return new PreparacionDTO(
                guardado.getId(),
                guardado.getEstado(),
                guardado.getTiempoEstimado(),
                guardado.getIdOrden()
        );
        
    } catch (Exception e) {
        log.error(
                "Error al crear preparación para orden {}",dto.getIdOrden(),e);

        throw e;
    }
    }

    // GET POR ID
    public PreparacionDTO obtenerPorId(Long id) {
        log.info(
            "Buscando preparación con id {}",id);

        Preparacion p =
                repository.findById(id)
                        .orElseThrow(() -> {
                                log.warn("Preparación {} no encontrada",id);
                                return new ResourceNotFoundException(
                                        "Preparacion no encontrada");
                        });
        return new PreparacionDTO(
                p.getId(),
                p.getEstado(),
                p.getTiempoEstimado(),
                p.getIdOrden()
        );
    }

    // UPDATE ESTADO
    public PreparacionDTO actualizarEstado(
            Long id,
            String estado) {
        log.info(
            "Actualizando preparación {} al estado {}",id,estado);
        Preparacion p =
                repository.findById(id)
                        .orElseThrow(() -> {
                                log.warn("Preparacion {} no encontrada",id);
                                return new ResourceNotFoundException(
                                        "Preparacion no encontrada");
                        });

        p.setEstado(estado);

        Preparacion actualizado =
                repository.save(p);
        log.info("Preparación {} actualizada correctamente",id);
        return new PreparacionDTO(
                actualizado.getId(),
                actualizado.getEstado(),
                actualizado.getTiempoEstimado(),
                actualizado.getIdOrden()
        );
    }

    // eliminar
    public void eliminar(Long id) {
        log.info(
            "Eliminando preparación {}",id);

    if (!repository.existsById(id)) {
        log.warn(
                "Se intentó eliminar preparación inexistente {}",id);

        throw new ResourceNotFoundException(
                "Preparacion no encontrada");
    }

    repository.deleteById(id);
    log.info(
            "Preparación {} eliminada correctamente",id);
    }
}