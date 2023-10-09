package com.techlab.codetest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
