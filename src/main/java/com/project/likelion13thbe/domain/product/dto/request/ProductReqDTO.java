package com.project.likelion13thbe.domain.product.dto.request;

import com.project.likelion13thbe.domain.product.entity.ProductType;
import lombok.Builder;

import java.time.LocalDateTime;

public class ProductReqDTO {

    @Builder
    public record ProductCreateReqDTO(
            Long productId,
            String name,
            String description,
            String image,
            Double price,
            ProductType productType,
            Integer productQuantity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
