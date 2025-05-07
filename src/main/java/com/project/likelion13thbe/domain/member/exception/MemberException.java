package com.project.likelion13thbe.domain.member.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class MemberException extends CustomException {
  public MemberException(MemberErrorCode errorCode) {
    super(errorCode);
  }
}
