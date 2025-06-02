package com.project.likelion13thbe.global.mail.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MailErrorCode implements BaseErrorCode {

    WRONG_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "MAIL400_1", "메일 주소가 잘못되었습니다");
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
