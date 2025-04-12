package com.project.likelion13thbe.domain.product.dto.response;

import com.project.likelion13thbe.domain.product.entity.ProductType;
import lombok.Builder;

public class ProductResponseDTO {

    @Builder
    public record ProductDetailResponseDTO(
            String name,
            String description,
            Integer price,
            String image,
            ProductType productType,
            Double ratingAvg
    ) {
    }


}
