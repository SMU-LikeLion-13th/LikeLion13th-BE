package com.project.likelion13thbe.domain.product.dto.request;

import com.project.likelion13thbe.domain.product.entity.ProductType;
import lombok.Builder;

import java.time.LocalDateTime;

public class ProductRequestDTO {

    @Builder
    public record ProductCreateRequestDTO(
            String name,
            String description,
            String productImage,
            Integer price,
            ProductType productType,
            Integer productQuantity,
            LocalDateTime createdAt,

            Long memberId
    ) {
    }
}
