package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class InvalidParameterException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public InvalidParameterException(ErrorCodeEnum errorCode, String target, String fieldName, String value) {
        super(String.format("%s [%s] (%s : %s)", errorCode.getMessage(), target, fieldName, value));
        this.errorCode = errorCode;
    }
}