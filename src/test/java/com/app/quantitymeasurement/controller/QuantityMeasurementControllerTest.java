package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInput;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAdd() throws Exception {

        QuantityDTO dto = new QuantityDTO(2.0, "FEET", "LengthUnit");

        Mockito.when(service.add(Mockito.any(), Mockito.any())).thenReturn(dto);

        QuantityInput input = new QuantityInput();
        input.setThisQuantity(new QuantityDTO(1, "FEET", "LengthUnit"));
        input.setThatQuantity(new QuantityDTO(12, "INCHES", "LengthUnit"));

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(2.0));
    }
    @Test
    void testSubtract() throws Exception {

        QuantityDTO dto = new QuantityDTO(0.0, "FEET", "LengthUnit");

        Mockito.when(service.subtract(Mockito.any(), Mockito.any()))
                .thenReturn(dto);

        QuantityInput input = new QuantityInput();
        input.setThisQuantity(new QuantityDTO(1, "FEET", "LengthUnit"));
        input.setThatQuantity(new QuantityDTO(12, "INCHES", "LengthUnit"));

        mockMvc.perform(post("/api/v1/quantities/subtract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(0.0));
    }
    @Test
    void testDivide() throws Exception {

        Mockito.when(service.divide(Mockito.any(), Mockito.any()))
                .thenReturn(2.0);

        QuantityInput input = new QuantityInput();
        input.setThisQuantity(new QuantityDTO(24, "INCHES", "LengthUnit"));
        input.setThatQuantity(new QuantityDTO(12, "INCHES", "LengthUnit"));

        mockMvc.perform(post("/api/v1/quantities/divide")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }
}