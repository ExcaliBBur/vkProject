package com.example.vkproject.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
