package com.project.likelion13thbe.domain.product.dto;

import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductReviewDTO {
    private final Product product;
    private final Double ratingAvg;
    private final Long reviewCount;
}
