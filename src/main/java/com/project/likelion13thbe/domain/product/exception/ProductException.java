package com.project.likelion13thbe.domain.product.exception;

import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class ProductException extends CustomException {

    public ProductException(ProductErrorCode productErrorCode) {
        super(productErrorCode);
    }

}
