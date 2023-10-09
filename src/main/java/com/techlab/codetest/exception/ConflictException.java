package com.techlab.codetest.exception;

public class ConflictException extends ApplicationException{
    public ConflictException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
