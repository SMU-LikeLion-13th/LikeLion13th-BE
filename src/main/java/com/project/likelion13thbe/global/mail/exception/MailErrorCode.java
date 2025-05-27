package com.project.likelion13thbe.global.mail.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum MailErrorCode implements BaseErrorCode {

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "SMTP401_1", "SMTP 인증 실패"),
    CONNECTION_FAILED(HttpStatus.SERVICE_UNAVAILABLE, "SMTP503_1", "SMTP 서버 연결 오류"),
    INVALID_RECIPIENT(HttpStatus.BAD_REQUEST, "SMTP400_1", "수신자 이메일 오류");

    private final HttpStatus status;
    private final String code;
    private final String message;
}