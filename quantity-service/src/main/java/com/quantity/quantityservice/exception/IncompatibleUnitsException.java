package com.quantity.quantityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompatibleUnitsException extends RuntimeException {
    public IncompatibleUnitsException(String a, String b) {
        super("Cannot operate on incompatible types: '" + a + "' and '" + b + "'");
    }
}
