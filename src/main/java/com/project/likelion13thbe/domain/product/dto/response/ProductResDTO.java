package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ProductResDTO {
    public record ProductResponeseDTO(
            Long ProductId,
            String content
    ){
    }

    @Builder
    public record ProductCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record ProductPreviewResDTO(
            Long id,
            String name,
            String content,
            String image,
            Integer price,
            Integer quantity,
            Double scoreAvg,
            Integer reviewCount,
            LocalDateTime createdAt
    ){
    }
}
