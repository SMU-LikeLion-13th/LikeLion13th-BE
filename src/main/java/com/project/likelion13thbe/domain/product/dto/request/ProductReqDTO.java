package com.project.likelion13thbe.domain.product.dto.request;

import lombok.Builder;
import lombok.Getter;

public class ProductReqDTO {

    @Getter
    @Builder
    public static class ProductCreateReqDTO {
        private String item;
        private int price;
        private int rating;
        private String description;
    }
}