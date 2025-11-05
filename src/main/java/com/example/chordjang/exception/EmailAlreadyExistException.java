package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class EmailAlreadyExistException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public EmailAlreadyExistException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
