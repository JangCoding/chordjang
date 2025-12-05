package com.example.chordjang.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Custom Exceptions

    @ExceptionHandler(TargetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTargetNotFound(TargetNotFoundException ex, HttpServletRequest req){
        return createResponseEntity(ex.getErrorCode().getCode(), ex.getMessage(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(TargetAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExist(TargetAlreadyExistException ex, HttpServletRequest req){
        return createResponseEntity(ex.getErrorCode().getCode(), ex.getMessage(), HttpStatus.CONFLICT, req);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handleWrongPassword(WrongPasswordException ex, HttpServletRequest req){
        return createResponseEntity(ex.getErrorCode().getCode(), ex.getMessage(), HttpStatus.UNAUTHORIZED, req);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParameter(InvalidParameterException ex, HttpServletRequest req){
        return createResponseEntity(ex.getErrorCode().getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST, req);
    }

    // Runtime Exceptions

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req){
        return createResponseEntity(ErrorCodeEnum.INVALID_REQUEST.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST, req);
    }

    // Helper Method

    private ResponseEntity<ErrorResponse> createResponseEntity(String code, String message, HttpStatus httpStatus, HttpServletRequest req) {
        ErrorResponse res = ErrorResponse.builder()
                .code(code)
                .message(message)
                .httpStatus(httpStatus.value())
                .path(req.getRequestURI())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(httpStatus).body(res);
    }
}
