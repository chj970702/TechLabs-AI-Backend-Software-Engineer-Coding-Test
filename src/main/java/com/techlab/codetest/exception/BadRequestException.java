package com.techlab.codetest.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends ApplicationException {
    public BadRequestException(final ErrorCode errorCode) {
        super(errorCode);
    }
}