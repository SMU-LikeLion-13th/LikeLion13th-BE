package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ProductResDTO {

    @Builder
    public record ProductPreviewResDTO(
            Long id,
            String title,
            String content,
            String image,
            Integer price,
            Integer quantity,
            Double ratingAvg,
            Integer reviewCount,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record ProductListDTO(
            List<ProductDTO> productList
    public record ProductCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){}

    ){
    }
}