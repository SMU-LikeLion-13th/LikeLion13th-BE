package com.project.likelion13thbe.domain.comment.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class CommentException extends CustomException {
    public CommentException(CommentErrorCode errorCode) {super(errorCode);}
}
