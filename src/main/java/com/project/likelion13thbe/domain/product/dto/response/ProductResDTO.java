package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Getter;

@Getter
public class ProductResDTO {
    public record ProductResponseDTO(
            Long id,
            String content
    ) {
    }
}
