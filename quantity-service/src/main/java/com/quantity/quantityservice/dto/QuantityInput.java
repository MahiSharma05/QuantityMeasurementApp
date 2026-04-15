package com.quantity.quantityservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class QuantityInput {

    @Valid @NotNull(message = "thisQuantity is required")
    private QuantityDTO thisQuantity;

    @Valid @NotNull(message = "thatQuantity is required")
    private QuantityDTO thatQuantity;

    private QuantityDTO targetUnit;   // optional: for explicit target unit in convert
}
