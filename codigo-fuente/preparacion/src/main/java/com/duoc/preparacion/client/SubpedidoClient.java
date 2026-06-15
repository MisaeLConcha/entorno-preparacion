package com.duoc.preparacion.client;

import com.duoc.preparacion.dto.SubpedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "subpedido-client",
        url = "${subpedido.service.url}"
)
public interface SubpedidoClient {

    @GetMapping("/api/v1/subpedidos/{id}")
    SubpedidoDTO getSubpedidoById(@PathVariable Long id);
}
