package com.techlab.codetest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(ApplicationException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ex.getExceptionResponse());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(ApplicationException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ex.getExceptionResponse());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity handleConflictException(ApplicationException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ex.getExceptionResponse());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
