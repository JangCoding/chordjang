package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class TargetNotFoundException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public TargetNotFoundException(ErrorCodeEnum errorCode, String target, String by, Object value) {
        super(String.format("%s (%s-%s : %s)", errorCode.getMessage(), target, by, value));
        this.errorCode = errorCode;
    }
}