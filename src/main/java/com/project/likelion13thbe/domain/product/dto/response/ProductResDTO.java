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
}
