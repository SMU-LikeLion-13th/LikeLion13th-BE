package com.project.likelion13thbe.domain.product.dto.request;

import lombok.Getter;

public class ProductReqDTO {
    @Getter
    public static class ProductCreateReqDTO {
        private String name;
        private String description;
        private Integer price;
        private Integer stock;
    }

    @Getter
    public static class ProductUpdateReqDTO {
        private String name;
        private String description;
        private Integer price;
        private Integer stock;
    }
}
