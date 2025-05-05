package com.project.likelion13thbe.domain.product.dto.request;

import com.project.likelion13thbe.domain.product.entity.ProductType;

public class ProductReqDTO {

    public record ProductCreateReqDTO(
            Long memberId,
            String name,
            String description,
            String image,
            Double price,
            ProductType productType,
            Integer productQuantity
    ) {
    }
}
