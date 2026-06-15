package com.duoc.preparacion.service;

import com.duoc.preparacion.client.NotificacionClient;
import com.duoc.preparacion.client.SubpedidoClient;
import com.duoc.preparacion.dto.*;
import com.duoc.preparacion.model.Preparacion;
import com.duoc.preparacion.repository.PreparacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreparacionService {

    @Autowired
    private PreparacionRepository repository;

    @Autowired
    private SubpedidoClient subpedidoClient;

    @Autowired
    private NotificacionClient notificacionClient;

    // CREAR
    public PreparacionDTO crearPreparacion(
        PreparacionCreateDTO dto) {

        // GET a subpedido
            SubpedidoDTO subpedido =
                subpedidoClient.getSubpedidoById(
                        dto.getIdOrden());

        if (subpedido == null) {
            throw new RuntimeException(
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

        } catch (Exception e) {

            System.out.println(
                    "No se pudo enviar notificacion");
        }

        // retornar DTO
        return new PreparacionDTO(
                guardado.getId(),
                guardado.getEstado(),
                guardado.getTiempoEstimado(),
                guardado.getIdOrden()
        );
    }

    // GET POR ID
    public PreparacionDTO obtenerPorId(Long id) {

        Preparacion p =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Preparacion no encontrada"));

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
        Preparacion p =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Preparacion no encontrada"));

        p.setEstado(estado);

        Preparacion actualizado =
                repository.save(p);
        return new PreparacionDTO(
                actualizado.getId(),
                actualizado.getEstado(),
                actualizado.getTiempoEstimado(),
                actualizado.getIdOrden()
        );
    }

    // DELETE
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}