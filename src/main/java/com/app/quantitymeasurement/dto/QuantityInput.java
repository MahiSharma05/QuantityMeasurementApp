package com.app.quantitymeasurement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityInput {
    private QuantityDTO thisQuantity;
    private QuantityDTO thatQuantity;
}