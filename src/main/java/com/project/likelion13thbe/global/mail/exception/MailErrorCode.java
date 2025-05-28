package com.project.likelion13thbe.global.mail.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MailErrorCode implements BaseErrorCode {

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "MAIL401_1", "SMTP 인증에 실패했습니다."),
    CONNECTION_FAILED(HttpStatus.SERVICE_UNAVAILABLE, "MAIL503_1", "SMTP 서버에 연결할 수 없습니다."),
    INVALID_RECIPIENT(HttpStatus.BAD_REQUEST, "MAIL400_1", "수신자 이메일 오류"),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "MAIL500_1", "알 수 없는 이메일 오류");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
