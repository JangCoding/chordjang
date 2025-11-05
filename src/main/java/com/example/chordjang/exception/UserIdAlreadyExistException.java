package com.example.chordjang.exception;

import lombok.Getter;

@Getter
public class UserIdAlreadyExistException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public UserIdAlreadyExistException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
