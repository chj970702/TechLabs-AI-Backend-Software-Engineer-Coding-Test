package com.techlab.codetest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final ExceptionResponse exceptionResponse;

    protected ApplicationException(final ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.exceptionResponse = new ExceptionResponse(errorCode.getMessage());
    }
}
