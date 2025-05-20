package com.project.likelion13thbe.global.error;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final AuthErrorCode errorCode;

    // AuthErrorCode 받아서 메시지 세팅
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // 메시지만 따로 받고 싶을 때도 (선택사항)
    public AuthException(String message) {
        super(message);
        this.errorCode = null;
    }
}
