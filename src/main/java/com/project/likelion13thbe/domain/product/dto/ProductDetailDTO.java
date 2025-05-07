package com.project.likelion13thbe.domain.product.dto;

import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.Builder;

@Builder
public record ProductDetailDTO (
        Product product,
        Double ratingAvg,
        Long reviewCount
){
}
