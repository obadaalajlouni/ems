package com.example.Ems.configuration;

public class NotFoundInDatabaseException extends Exception {
    public NotFoundInDatabaseException() {
    }

    public NotFoundInDatabaseException(String message) {
        super(message);
    }
}
