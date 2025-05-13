package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return Product.builder()
                .item(productCreateReqDTO.getItem())
                .price(productCreateReqDTO.getPrice())
                .rating(productCreateReqDTO.getRating())
                .description(productCreateReqDTO.getDescription())
                .build();
    }
    public static ProductResDTO.ProductCreateResDTO toProductResDTO(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
