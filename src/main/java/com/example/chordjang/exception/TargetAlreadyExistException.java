package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class TargetAlreadyExistException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public TargetAlreadyExistException(ErrorCodeEnum errorCode, String target, String fieldName, String value) {
        super(String.format("%s %s 입니다. (%s : %s)", errorCode.getMessage(), target, fieldName, value));
        this.errorCode = errorCode;
    }
}
