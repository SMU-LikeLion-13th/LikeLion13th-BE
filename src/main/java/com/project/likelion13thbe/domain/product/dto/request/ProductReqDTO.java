package com.project.likelion13thbe.domain.product.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

public class ProductReqDTO {
    @Builder
    public record ProductCreateReqDTO(
            String name,
            String description,
            String image,
            Integer price,
            Integer quantity,
            double rating,
            LocalDateTime createdAt
    ) {
    }
}
