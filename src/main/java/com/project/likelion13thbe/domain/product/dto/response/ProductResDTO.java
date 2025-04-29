package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
            Double rating
    ) {
    }

    @Builder
    public record ProductListResDTO(
            List<ProductDetailResDTO> productList
    ) {
    }

    @Builder
    public record ProductCreateResDTO(
            Long productId
    ) {
    }

}
