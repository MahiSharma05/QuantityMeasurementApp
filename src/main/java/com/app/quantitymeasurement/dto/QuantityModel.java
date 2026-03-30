package com.app.quantitymeasurement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityModel<T> {
    private double value;
    private T unit;
}