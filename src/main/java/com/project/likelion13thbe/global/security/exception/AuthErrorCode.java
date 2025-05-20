package com.project.likelion13thbe.global.security.exception;


import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH401_1", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH403_1", "권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_2", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_3", "토큰이 만료되었습니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "AUTH401_4", "로그인에 실패하였습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH404_1", "권한을 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String code;
    private String message;

}
