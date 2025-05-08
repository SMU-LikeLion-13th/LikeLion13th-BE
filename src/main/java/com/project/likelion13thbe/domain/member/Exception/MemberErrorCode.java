package com.project.likelion13thbe.domain.member.Exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400", "해당 사용자를 찾지 못했습니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "email에 해당하는 요청한 사용자을 찾을 수 없습니다")
;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
