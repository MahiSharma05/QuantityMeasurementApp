package com.quantity.quantityservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request body for POST /quantity/convert
 *
 * Works for ALL unit types — length, weight, volume, temperature.
 *
 * Example:
 * {
 *   "value": 100,
 *   "fromUnit": "CELSIUS",
 *   "toUnit": "FAHRENHEIT"
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertRequest {

    @NotNull(message = "value is required")
    private Double value;

    @NotBlank(message = "fromUnit is required (e.g. CELSIUS, FEET, KILOGRAM)")
    private String fromUnit;

    @NotBlank(message = "toUnit is required (e.g. FAHRENHEIT, METER, GRAM)")
    private String toUnit;
}