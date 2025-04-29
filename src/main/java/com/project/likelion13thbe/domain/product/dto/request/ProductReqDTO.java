package com.project.likelion13thbe.domain.product.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

public class ProductReqDTO {
    @Builder
    public record ProductCreateReqDTO(
            String name,
            String description,
            String imageUrl,
            Integer price,
            Integer productQuantity,
            LocalDateTime createdAt
    ) {
    }
}
