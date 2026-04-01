package com.app.quantitymeasurement;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInput;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuantityMeasurementApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    //  ADD API
    @Test
    void testAddAPI() {

        QuantityInput input = new QuantityInput();
        input.setThisQuantity(new QuantityDTO(1, "FEET", "LengthUnit"));
        input.setThatQuantity(new QuantityDTO(12, "INCHES", "LengthUnit"));

        ResponseEntity<QuantityDTO> response =
                restTemplate.postForEntity("/api/v1/quantities/add", input, QuantityDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    //  COMPARE API
    @Test
    void testCompareAPI() {

        QuantityInput input = new QuantityInput();
        input.setThisQuantity(new QuantityDTO(12, "INCHES", "LengthUnit"));
        input.setThatQuantity(new QuantityDTO(1, "FEET", "LengthUnit"));

        ResponseEntity<Boolean> response =
                restTemplate.postForEntity("/api/v1/quantities/compare", input, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    //  ERROR TEST
    @Test
    void testInvalidInput() {

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/v1/quantities/add", null, String.class);

        assertTrue(response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError());
    }
}