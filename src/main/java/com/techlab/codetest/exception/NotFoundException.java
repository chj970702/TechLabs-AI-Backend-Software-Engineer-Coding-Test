package com.techlab.codetest.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
