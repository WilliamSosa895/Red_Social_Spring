package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNotFound(EntityNotFoundException e) {
        Map<String,String> m=new HashMap<>();
        m.put("error", e.getMessage());
        return new ResponseEntity<>(m, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException e) {
        Map<String,String> m=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fe -> m.put(fe.getField(), fe.getDefaultMessage()));
        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleAll(Exception e) {
        Map<String,String> m=new HashMap<>();
        m.put("error", e.getMessage());
        return new ResponseEntity<>(m, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}