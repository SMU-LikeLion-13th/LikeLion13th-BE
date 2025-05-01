package com.project.likelion13thbe.domain.product.dto.response;

import com.project.likelion13thbe.domain.product.entity.ProductType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ProductResDTO {

    @Builder
    public record ProductDetailResDTO(
            Long productId,
            String name,
            String description,
            String image,
            Double price,
            ProductType productType,
            Integer ProductQuantity,
            Double ratingAvg,
            Integer reviewCount
    ) {
    }

    @Builder
    public record ProductListResDTO(
            List<ProductDetailResDTO> products
    ) {
    }

    @Builder
    public record ProductCreateResDTO(
            Long productId,
            LocalDateTime createdAt
    ) {
    }

}
