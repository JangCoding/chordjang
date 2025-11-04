package com.example.chordjang.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;


@Getter
public class ErrorResponse {
    private final String code;    // 에러 코드
    private final String message; // 에러 메세지
    private final int httpStatus; // HTTP 상태 코드
    private final String path;    // 요청 경로
    private final Instant timestamp;  //발생 시간

    @Builder
    private ErrorResponse(String code, String message, int httpStatus, String path, Instant timestamp){
        this.code=code;
        this.message=message;
        this.httpStatus = httpStatus;
        this.path=path;
        this.timestamp=timestamp;
    }

}
