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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, HttpServletRequest req){
        ErrorResponse res = buildRes(ErrorCodeEnum.USER_NOT_FOUND, ex, HttpStatus.NOT_FOUND, req);
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }

    @ExceptionHandler({ EmailAlreadyExistException.class, UserIdAlreadyExistException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExist(RuntimeException ex, HttpServletRequest req){

        ErrorCodeEnum errCode = (ex instanceof EmailAlreadyExistException)
                ? ((EmailAlreadyExistException) ex).getErrorCode()
                : ((UserIdAlreadyExistException) ex).getErrorCode();

        ErrorResponse res = buildRes(errCode, ex, HttpStatus.CONFLICT, req);
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req){
        ErrorResponse res = buildRes(ErrorCodeEnum.INVALID_REQUEST, ex, HttpStatus.BAD_REQUEST, req);
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(WrongPasswordException ex, HttpServletRequest req){
        ErrorResponse res = buildRes(ErrorCodeEnum.INVALID_PASSWORD, ex, HttpStatus.UNAUTHORIZED, req);
        return ResponseEntity.status(res.getHttpStatus()).body(res);
    }

    private ErrorResponse buildRes(ErrorCodeEnum errCode, Exception ex, HttpStatus httpStatus, HttpServletRequest req){
        log.warn("{} : {}", ex.getClass().getSimpleName(), ex.getMessage());

        return ErrorResponse.builder()
            .code(errCode.getCode())
            .message(ex.getMessage())
            .httpStatus(httpStatus.value())
            .path(req.getRequestURI())
            .timestamp(Instant.now())
            .build();
    }
}
