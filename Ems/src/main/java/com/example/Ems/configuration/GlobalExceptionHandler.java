package com.example.Ems.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
            (NotFoundInDatabaseException.class)
    public ResponseEntity<?> handleNotFoundInDatabaseException(NotFoundInDatabaseException ex){
        return ResponseEntity
                .badRequest().body(
                        ex.getMessage()
                );
    }
    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException ex){
        System.out.println("handleException");
        return ResponseEntity
                .badRequest().body(
                        ex.getMessage()
                );
    }
}
