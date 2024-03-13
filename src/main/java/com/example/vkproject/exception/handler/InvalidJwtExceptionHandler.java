package com.example.vkproject.exception.handler;

import com.example.vkproject.exception.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class InvalidJwtExceptionHandler {
    @ExceptionHandler(value = InvalidJwtException.class)
    public ResponseEntity<Map<String, String>> handleServiceUnavailableException(InvalidJwtException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
