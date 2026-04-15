package com.quantity.quantityservice.dto;

import com.quantity.quantityservice.core.IMeasurable;
import lombok.*;

/** Internal model — not exposed in API responses */
@Data @AllArgsConstructor @NoArgsConstructor
public class QuantityModel<T extends IMeasurable> {
    private double value;
    private T unit;
}