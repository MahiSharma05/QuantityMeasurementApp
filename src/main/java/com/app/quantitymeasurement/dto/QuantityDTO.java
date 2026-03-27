package com.app.quantitymeasurement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityDTO {
    private double value;
    private String unit;
    private String measurementType;
}