package com.example.vkproject.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.ServiceUnavailableException;
import java.util.Map;

@RestControllerAdvice
public class ServiceUnavailableExceptionHandler {
    @ExceptionHandler(value = ServiceUnavailableException.class)
    public ResponseEntity<Map<String, String>> handleServiceUnavailableException(ServiceUnavailableException exception) {
        return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
