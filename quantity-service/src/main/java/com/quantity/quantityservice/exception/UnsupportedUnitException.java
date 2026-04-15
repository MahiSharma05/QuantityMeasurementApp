package com.quantity.quantityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedUnitException extends RuntimeException {
    public UnsupportedUnitException(String message) { super(message); }
}
