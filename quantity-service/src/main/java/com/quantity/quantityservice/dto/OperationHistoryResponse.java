package com.quantity.quantityservice.dto;

import com.quantity.quantityservice.entity.OperationHistory;
import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO returned to clients — exposes entity data safely,
 * allows entity to change internally without breaking the API contract.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationHistoryResponse {

    private Long id;
    private String username;
    private String operationType;
    private String inputData;
    private String result;
    private LocalDateTime timestamp;

    /** Maps entity → DTO in one place */
    public static OperationHistoryResponse from(OperationHistory entity) {
        return OperationHistoryResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .operationType(entity.getOperationType())
                .inputData(entity.getInputData())
                .result(entity.getResult())
                .timestamp(entity.getTimestamp())
                .build();
    }
}