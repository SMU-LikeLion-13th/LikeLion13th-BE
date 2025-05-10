package com.project.likelion13thbe.domain.product.exception;

import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements BaseErrorCode {

    PRODUCT_NOT_CODE(HttpStatus.NOT_FOUND, "PRODUCT404_1", "상품을 찾을 수 없음");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
