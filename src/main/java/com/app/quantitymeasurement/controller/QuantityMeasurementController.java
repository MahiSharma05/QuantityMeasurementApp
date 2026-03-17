package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInput;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // ================= ADD =================
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(@RequestBody QuantityInput input) {
        QuantityDTO result = service.add(
                input.getThisQuantity(),
                input.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }

    // ================= COMPARE =================
    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(@RequestBody QuantityInput input) {
        boolean result = service.compare(
                input.getThisQuantity(),
                input.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }

    // ================= CONVERT =================
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(@RequestBody QuantityInput input) {
        QuantityDTO result = service.convert(
                input.getThisQuantity(),
                input.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }

    // ================= SUBTRACT =================
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(@RequestBody QuantityInput input) {
        QuantityDTO result = service.subtract(
                input.getThisQuantity(),
                input.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }

    // ================= DIVIDE =================
    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@RequestBody QuantityInput input) {
        double result = service.divide(
                input.getThisQuantity(),
                input.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }
}