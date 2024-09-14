package com.globalseguros.lead.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalseguros.lead.entity.Lead;
import com.globalseguros.lead.enums.Ciudad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LeadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void prueba() throws Exception {
        // Crear una instancia de Lead para enviar en la solicitud
        Lead lead = new Lead();
        lead.setNomCliente("Cliente Prueba");
        lead.setNit("123456789");
        lead.setNomPunto("Punto Prueba");
        lead.setNomEquipo("Equipo Prueba");
        lead.setCiudad(Ciudad.CALI);
        lead.setRtc("987654321");
        lead.setPromotor("Promotor Prueba");
        lead.setUsuario("usuario_prueba");
        lead.setTratamientoDatos(true);

        // Simular una petición POST al endpoint para crear un Lead
        mockMvc.perform(post("/api/v1/leads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(lead)))
                .andExpect(status().isCreated());
    }
}
