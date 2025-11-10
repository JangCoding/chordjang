package com.example.chordjang.exception;

import lombok.Getter;

@Getter

public class WrongPasswordException extends RuntimeException{
    private final ErrorCodeEnum errorCode;

    public WrongPasswordException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
