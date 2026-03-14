package com.apps.quantitymeasurement.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static DatabaseException queryFailed(String query, Exception e) {
        return new DatabaseException("Database query failed: " + query, e);
    }
}