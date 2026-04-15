package com.quantity.quantityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String operationType;       // ADD, SUBTRACT, COMPARE, CONVERT, DIVIDE, CONVERT_UNITS

    @Column(columnDefinition = "TEXT")  // input can be long JSON
    private String inputData;

    @Column(columnDefinition = "TEXT")
    private String result;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}