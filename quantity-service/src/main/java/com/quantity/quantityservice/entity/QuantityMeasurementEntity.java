package com.quantity.quantityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class QuantityMeasurementEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation;          // ADD, SUBTRACT, COMPARE, CONVERT, DIVIDE

    private Double resultValue;        // null for COMPARE
    private String resultUnit;
    private String resultMeasurementType;
    private String resultString;       // for COMPARE ("true"/"false") and DIVIDE (ratio)

    private boolean isError;
    private String errorMessage;

    private String performedBy;        // from X-Authenticated-User header

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = LocalDateTime.now(); }
}
