package com.project.likelion13thbe.global.security.exception;

import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class AuthException extends CustomException {
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}