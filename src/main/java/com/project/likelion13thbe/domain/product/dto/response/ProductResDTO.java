package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProductResDTO {
    public record ProductResponseDTO(
            Long id,
            String content
    ) {}

    public record ProductCreateResDTO(
            Long productId,
            LocalDateTime createdAt
    ) {}

    public record ProductPreviewResDTO(
            Long productId,
            String productName,
            Long price,
            Long stars
    ) {}

    public record ProductOffsetResDTO (
            List<ProductPreviewResDTO> products,
            Long totalElements,
            Integer totalPages
    ) {}

    public record ProductCursorResDTO (
            List<ProductPreviewResDTO> products,
            Long nextCursor,
            Boolean hasNext
    ) {}


}
