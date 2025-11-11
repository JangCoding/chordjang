package com.example.chordjang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    // 400 BAD_REQUEST
    INVALID_REQUEST(400, "INVALID_REQUEST", "잘못된 요청입니다."),
    INVALID_PARAMETER(400, "INVALID_PARAMETER", "요청 파라미터가 유효하지 않습니다."),

    // 401 UNAUTHORIZED
    UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),
    INVALID_TOKEN(401, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    INVALID_PASSWORD(401, "INVALID_PASSWORD", "유효하지 않은 비밀번호입니다."),

    // 403 FORBIDDEN
    FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다."),

    // 404 NOT_FOUND
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "찾을 수 없는 사용자입니다."),
    POST_NOT_FOUND(404, "POST_NOT_FOUND", "존재하지 않는 게시물입니다."),

    // 409 CONFLICT
    CONFLICT(409, "CONFLICT", "데이터 충돌이 발생했습니다."),
    ALREADY_EXIST_USERID(409, "ALREADY_EXIST_USERID", "이미 등록된 유저 아이디 입니다."),
    ALREADY_EXIST_EMAIL(409, "ALREADY_EXIST_EMAIL", "이미 등록된 이메일입니다."),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;
}
