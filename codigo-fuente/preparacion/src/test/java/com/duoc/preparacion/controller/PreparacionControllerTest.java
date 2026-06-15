package com.duoc.preparacion.controller;

import com.duoc.preparacion.dto.PreparacionCreateDTO;
import com.duoc.preparacion.dto.PreparacionDTO;
import com.duoc.preparacion.service.PreparacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PreparacionControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper =
            new ObjectMapper();

    @Mock
    private PreparacionService service;

    @InjectMocks
    private PreparacionController controller;

    @BeforeEach
    void setUp() {
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void deberiaRetornarPreparacionPorId()
        throws Exception {

            // Arrange
            PreparacionDTO dto =
                new PreparacionDTO(
                    1L,
                    "INICIADO",
                    20,
                    1L
                );

            when(service.obtenerPorId(1L))
                    .thenReturn(dto);
            // Act + Assert
            mockMvc.perform(
                get("/api/v1/preparacion/1"))
                .andExpect(status().isOk())
                .andExpect(
                    jsonPath("$.id")
                        .value(1))
                .andExpect(
                    jsonPath("$.estado")
                        .value("INICIADO"))
                .andExpect(
                    jsonPath("$.tiempoEstimado")
                        .value(20));
        }

    @Test
    void deberiaCrearPreparacion()
        throws Exception {
            // Arrange
            PreparacionCreateDTO entrada =
                new PreparacionCreateDTO(
                    1L,
                    20
                );
            PreparacionDTO salida =
                new PreparacionDTO(
                    1L,
                    "INICIADO",
                    20,
                    1L
                    );
            when(service.crearPreparacion(any()))
                .thenReturn(salida);
            // Act + Assert
            mockMvc.perform(
                post("/api/v1/preparacion")
                    .contentType(
                        MediaType.APPLICATION_JSON)
                    .content(
                        objectMapper
                            .writeValueAsString(
                                entrada)))
                    .andExpect(status().isCreated())
                    .andExpect(
                        jsonPath("$.estado")
                            .value("INICIADO"))
                    .andExpect(
                        jsonPath("$.tiempoEstimado")
                            .value(20));
        }

    @Test
    void deberiaEliminarPreparacion()
        throws Exception {
            // Arrange
            doNothing()
                .when(service)
                .eliminar(1L);

            // Act + Assert
            mockMvc.perform(
                delete(
                    "/api/v1/preparacion/1"))
                    .andExpect(
                        status().isNoContent());
        }
}