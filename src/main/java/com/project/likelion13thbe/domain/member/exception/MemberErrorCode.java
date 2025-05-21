package com.project.likelion13thbe.domain.member.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_ERROR_CODE(HttpStatus.NOT_FOUND,"MEMBER404_1","회원을 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
