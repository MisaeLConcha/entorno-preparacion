package com.duoc.preparacion.service;

import com.duoc.preparacion.client.NotificacionClient;
import com.duoc.preparacion.client.SubpedidoClient;
import com.duoc.preparacion.dto.PreparacionCreateDTO;
import com.duoc.preparacion.dto.PreparacionDTO;
import com.duoc.preparacion.dto.SubpedidoDTO;
import com.duoc.preparacion.exception.ResourceNotFoundException;
import com.duoc.preparacion.model.Preparacion;
import com.duoc.preparacion.repository.PreparacionRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PreparacionServiceTest {

    @Mock
    private PreparacionRepository repository;

    @Mock
    private SubpedidoClient subpedidoClient;

    @Mock
    private NotificacionClient notificacionClient;

    @InjectMocks
    private PreparacionService service;

        @Test
        void deberiaCrearPreparacion() {

                // Arrange

                PreparacionCreateDTO dto =
                        new PreparacionCreateDTO(
                                1L,
                                20);

                Preparacion guardado =
                        new Preparacion();

                guardado.setId(1L);
                guardado.setEstado("INICIADO");
                guardado.setTiempoEstimado(20);
                guardado.setIdOrden(1L);

                when(subpedidoClient.getSubpedidoById(1L))
                        .thenReturn(new SubpedidoDTO());

                when(repository.save(any(Preparacion.class)))
                        .thenReturn(guardado);

                // Act

                PreparacionDTO resultado =
                        service.crearPreparacion(dto);

                // Assert

                assertEquals(
                        "INICIADO",
                        resultado.getEstado());
                }

        @Test
        void deberiaObtenerPreparacionPorId() {

                // Arrange

                Preparacion p = new Preparacion();

                p.setId(1L);
                p.setEstado("LISTO");
                p.setTiempoEstimado(30);

                when(repository.findById(1L))
                        .thenReturn(Optional.of(p));

                // Act

                PreparacionDTO dto =
                        service.obtenerPorId(1L);

                // Assert

                assertEquals(
                        "LISTO",
                        dto.getEstado());
                }

        @Test
        void deberiaLanzarExcepcionSiNoExistePreparacion() {

                // Arrange

                when(repository.findById(1L))
                        .thenReturn(Optional.empty());

                // Act + Assert

                assertThrows(
                        ResourceNotFoundException.class,
                        () -> service.obtenerPorId(1L));
                }
}