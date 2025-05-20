package com.project.likelion13thbe.global.Security;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AuthErrorCode implements BaseErrorCode {

    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근이 금지되었습니다"),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 자원을 찾을 수 없습니다"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "VALID400_0", "잘못된 파라미터 입니다.")
            ;

    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;
}
