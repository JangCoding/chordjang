package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class TargetAlreadyExistException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public TargetAlreadyExistException(ErrorCodeEnum errorCode, String fieldName, String value) {
        super(String.format("%s (%s : %s)", errorCode.getMessage(), fieldName, value));
        this.errorCode = errorCode;
    }
}
