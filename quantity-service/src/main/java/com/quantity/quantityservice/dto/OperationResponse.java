package com.quantity.quantityservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class OperationResponse<T> {
    private T result;
    private String operation;
    private LocalDateTime timestamp;
    private String authenticatedUser;

    public static <T> OperationResponse<T> of(T result, String operation, String user) {
        return new OperationResponse<>(result, operation, LocalDateTime.now(), user);
    }
}
