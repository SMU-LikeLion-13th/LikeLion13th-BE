package com.project.likelion13thbe.domain.product.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;

public class ProductException extends CustomException {

    public ProductException(ProductErrorCode errorCode) {
        super(errorCode);
    }
}
