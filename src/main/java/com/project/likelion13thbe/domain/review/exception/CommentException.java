package com.project.likelion13thbe.domain.review.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class CommentException extends CustomException {

    public CommentException(CommentErrorCode commentErrorCode) {
        super(commentErrorCode);
    }
}
