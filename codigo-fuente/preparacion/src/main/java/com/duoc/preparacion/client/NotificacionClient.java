package com.duoc.preparacion.client;

import com.duoc.preparacion.dto.NotificacionDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notificacion-client",
        url = "${notificacion.service.url}"
)
public interface NotificacionClient {

    @PostMapping("/api/v1/notificaciones")
    void crearNotificacion(
            @RequestBody NotificacionDTO dto);
}