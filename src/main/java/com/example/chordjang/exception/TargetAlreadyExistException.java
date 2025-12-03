package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class TargetAlreadyExistException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public TargetAlreadyExistException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
