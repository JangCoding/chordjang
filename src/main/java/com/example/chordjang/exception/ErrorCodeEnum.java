package com.example.chordjang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    // 400 BAD_REQUEST
    INVALID_REQUEST(400, "INVALID_REQUEST", "잘못된 요청입니다."),
    INVALID_PARAMETER(400, "INVALID_PARAMETER", "요청 파라미터가 유효하지 않습니다."),
    INVALID_DTO_FIELD(400, "INVALID_DTO_FIELD", "요청 DTO 필드가 유효하지 않습니다."),

    // 401 UNAUTHORIZED
    UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),
    INVALID_TOKEN(401, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    INVALID_PASSWORD(401, "INVALID_PASSWORD", "유효하지 않은 비밀번호입니다."),

    // 403 FORBIDDEN
    FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다."),

    // 404 NOT_FOUND
    TARGET_NOT_FOUND(404, "TARGET_NOT_FOUND", "대상를 찾을 수 없습니다."),

    // 409 CONFLICT
    CONFLICT(409, "CONFLICT", "데이터 충돌이 발생했습니다."),
    ALREADY_EXIST_TARGET(409, "ALREADY_EXIST_USERID", "이미 등록된 "),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;
}
