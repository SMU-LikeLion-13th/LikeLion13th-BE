package com.project.likelion13thbe.global.security.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_1", "토큰이 유효하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUNT(HttpStatus.UNAUTHORIZED, "AUTH401_2", "refresh 토큰이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
