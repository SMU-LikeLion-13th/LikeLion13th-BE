package com.project.likelion13thbe.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProductResDTO {

    @Builder
    public record ProductDTO(
            Long productId,
            String name,
            String content,
            String image,
            Double price,
            Integer quantity,
            Double ratingAvg,
            Integer reviewCount
    ){
    }

    @Builder
    public record ProductListDTO(
            List<ProductDTO> productList
    ){
    }
}