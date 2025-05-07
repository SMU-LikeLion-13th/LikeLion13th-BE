package com.project.likelion13thbe.domain.member.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class MemberException extends CustomException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}
