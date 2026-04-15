package com.quantity.quantityservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class QuantityDTO {
    private double value;

    @NotBlank(message = "Unit is required (e.g. FEET, KILOGRAM, LITER)")
    private String unit;

    private String measurementType;   // resolved by service, optional in request
}
