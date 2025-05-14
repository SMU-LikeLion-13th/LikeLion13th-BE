package com.project.likelion13thbe.domain.comment.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class CommentException extends CustomException {

    public CommentException(CommentErrorCode commentErrorCode) {
        super(commentErrorCode);
    }
}
