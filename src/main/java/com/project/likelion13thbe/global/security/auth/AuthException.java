package com.project.likelion13thbe.global.security.auth;

import com.project.likelion13thbe.global.apiPayload.code.AuthErrorCode;
import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.AllArgsConstructor;


public class AuthException extends CustomException {

    public AuthException(BaseErrorCode errorCode) {
        super(errorCode);
    }







}
