package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public UnauthorizedException(ErrorCodeEnum errorCode, String msg) {
        super(String.format("%s %s", errorCode.getMessage(), msg));
        this.errorCode = errorCode;
    }
}
