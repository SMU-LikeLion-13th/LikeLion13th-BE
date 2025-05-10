package com.project.likelion13thbe.domain.review.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class ReviewException extends CustomException {

    public ReviewException(ReviewErrorCode reviewErrorCode) {
        super(reviewErrorCode);
    }
}
