package com.project.likelion13thbe.global.error;

import lombok.Getter;

@Getter
public enum AuthErrorCode {
    INVALID_TOKEN("AUTH_001", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN("AUTH_002", "만료된 토큰입니다."),
    _FORBIDDEN("AUTH_003", "접근 권한이 없습니다."),
    _NOT_FOUND("AUTH_004", "리소스를 찾을 수 없습니다."),
    _UNAUTHORIZED("AUTH_005", "인증이 필요합니다.");

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}


