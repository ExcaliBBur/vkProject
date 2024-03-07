package com.example.vkproject.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
