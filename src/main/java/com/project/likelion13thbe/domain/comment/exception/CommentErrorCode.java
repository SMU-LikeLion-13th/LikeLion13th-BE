package com.project.likelion13thbe.domain.comment.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {

    Comment_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment404_1", "회원찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
