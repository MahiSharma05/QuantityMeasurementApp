package com.quantity.quantityservice.controller;

import com.quantity.quantityservice.dto.*;
import com.quantity.quantityservice.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quantity.quantityservice.dto.OperationHistoryResponse;
import java.util.List;
/**
 * ★ Added: POST /quantity/convert
 *   A single, clean API that handles ALL unit types including Temperature.
 *
 * Existing endpoints (/add, /subtract, /compare, /convert, /divide) unchanged.
 */
@RestController
@Tag(name = "Quantity Measurement")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    private String getUser(String header) {
        return (header != null && !header.isBlank()) ? header : "anonymous";
    }

    /**
     * POST /quantity/convert
     *
     * Converts any supported unit to any other unit of the same type.
     * Temperature uses proper formulas; all others use linear scaling.
     *
     * Request:  { "value": 100, "fromUnit": "CELSIUS", "toUnit": "FAHRENHEIT" }
     * Response: { "result": 212.0, "fromUnit": "CELSIUS", "toUnit": "FAHRENHEIT", "inputValue": 100.0 }
     *
     * Works for all types:
     *   { "value": 5, "fromUnit": "FEET", "toUnit": "METER" }
     *   { "value": 1, "fromUnit": "KILOGRAM", "toUnit": "GRAM" }
     *   { "value": 1, "fromUnit": "LITRE", "toUnit": "MILLILITRE" }
     */
    @PostMapping("/quantity/convert")
    @Operation(summary = "Convert between any units — including Temperature")
    public ResponseEntity<ConvertResponse> convertUnits(
            @Valid @RequestBody ConvertRequest request,
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {

        ConvertResponse response = service.convertUnits(request, getUser(user));
        return ResponseEntity.ok(response);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Existing endpoints (unchanged, kept under /api/v1/quantities/**)
    // ══════════════════════════════════════════════════════════════════════════

    @PostMapping("/api/v1/quantities/add")
    @Operation(summary = "Add two quantities (same type)")
    public ResponseEntity<OperationResponse<QuantityDTO>> add(
            @Valid @RequestBody QuantityInput input,
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {
        var result = service.add(input.getThisQuantity(), input.getThatQuantity(), getUser(user));
        return ResponseEntity.ok(OperationResponse.of(result, "ADD", getUser(user)));
    }

    @PostMapping("/api/v1/quantities/subtract")
    @Operation(summary = "Subtract thatQuantity from thisQuantity")
    public ResponseEntity<OperationResponse<QuantityDTO>> subtract(
            @Valid @RequestBody QuantityInput input,
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {
        var result = service.subtract(input.getThisQuantity(), input.getThatQuantity(), getUser(user));
        return ResponseEntity.ok(OperationResponse.of(result, "SUBTRACT", getUser(user)));
    }

    @PostMapping("/api/v1/quantities/compare")
    @Operation(summary = "Compare two quantities for equality")
    public ResponseEntity<OperationResponse<Boolean>> compare(
            @Valid @RequestBody QuantityInput input,
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {
        boolean result = service.compare(
                input.getThisQuantity(), input.getThatQuantity(), getUser(user));
        return ResponseEntity.ok(OperationResponse.of(result, "COMPARE", getUser(user)));
    }

    @PostMapping("/api/v1/quantities/convert")
    @Operation(summary = "Convert thisQuantity to the unit of thatQuantity")
    public ResponseEntity<OperationResponse<QuantityDTO>> convert(
            @Valid @RequestBody QuantityInput input,
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {
        var result = service.convert(
                input.getThisQuantity(), input.getThatQuantity(), getUser(user));
        return ResponseEntity.ok(OperationResponse.of(result, "CONVERT", getUser(user)));
    }

    @PostMapping("/api/v1/quantities/divide")
    @Operation(summary = "Divide thisQuantity by thatQuantity")
    public ResponseEntity<OperationResponse<Double>> divide(
            @Valid @RequestBody QuantityInput input,
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {
        double result = service.divide(
                input.getThisQuantity(), input.getThatQuantity(), getUser(user));
        return ResponseEntity.ok(OperationResponse.of(result, "DIVIDE", getUser(user)));
    }

    /**
     * GET /api/v1/history/user
     * Returns all operations performed by the currently authenticated user.
     * Username is read from the X-Authenticated-User header set by the API Gateway.
     */
    @GetMapping("/api/v1/history/user")
    @Operation(summary = "Get operation history for the logged-in user")
    public ResponseEntity<List<OperationHistoryResponse>> getUserHistory(
            @RequestHeader(value = "X-Authenticated-User", required = false) String user) {

        String username = (user != null && !user.isBlank()) ? user : "anonymous";
        return ResponseEntity.ok(service.getUserHistory(username));
    }
}