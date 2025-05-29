package com.project.likelion13thbe.global.mail.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class MailException extends CustomException {
  public MailException(MailErrorCode errorCode) {
    super(errorCode);
  }
}
