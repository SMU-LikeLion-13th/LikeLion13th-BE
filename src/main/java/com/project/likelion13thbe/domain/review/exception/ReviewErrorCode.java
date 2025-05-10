package com.project.likelion13thbe.domain.review.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_CODE(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
