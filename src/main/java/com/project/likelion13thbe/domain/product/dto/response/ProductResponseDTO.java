package com.project.likelion13thbe.domain.product.dto.response;

import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.entity.ProductType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ProductResponseDTO {

    @Builder
    public record ProductDetailResponseDTO(
            Long productId,
            String name,
            String description,
            Integer price,
            String image,
            ProductType productType,
            Double ratingAvg
    ) {
    }

    @Builder
    public record ProductListResponseDTO(
            List<ProductDetailResponseDTO> productList
    ) {
    }

    @Builder
    public record ProductCreateResponseDTO(
            Long productId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ProductCursorResponseDTO(
            List<ProductDetailResponseDTO> products,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}
