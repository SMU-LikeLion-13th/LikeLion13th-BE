package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProductResDTO {

    @Builder
    public record ProductDetailResDTO(
            Long productId,
            String name,
            Integer price,
            String image,
            String description,
            double rating
    ) {}

    @Builder
    public record ProductListResDTO(
            List<ProductDetailResDTO> productList
    ) {}

    @Builder
    public record ProductCreateResDTO(
            Long productId,
            LocalDateTime createdAt

    ) {}

}
