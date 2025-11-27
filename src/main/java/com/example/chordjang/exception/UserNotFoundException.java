package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public UserNotFoundException(ErrorCodeEnum errorCode, String by, Object value) {
        super(String.format("%s (%s: %s)", errorCode.getMessage(), by, value));
        this.errorCode = errorCode;
    }
}