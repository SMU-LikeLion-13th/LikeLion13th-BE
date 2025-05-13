package com.project.likelion13thbe.domain.review.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class ReviewEception extends CustomException {
    public ReviewEception(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
