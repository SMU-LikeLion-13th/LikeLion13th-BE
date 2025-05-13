package com.project.likelion13thbe.domain.review.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class ReviewException extends CustomException {
  public ReviewException(ReviewErrorCode errorCode) {
    super(errorCode);
  }
}
