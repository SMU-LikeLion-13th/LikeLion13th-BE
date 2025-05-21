package com.project.likelion13thbe.global.Security;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class AuthException extends CustomException {

    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
