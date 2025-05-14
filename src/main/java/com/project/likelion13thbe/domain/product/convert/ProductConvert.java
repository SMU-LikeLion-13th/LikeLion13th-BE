package com.project.likelion13thbe.domain.product.convert;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConvert {
    public static Product toProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return Product.builder()
                .name(productCreateReqDTO.name())
                .description(productCreateReqDTO.description())
                .price(productCreateReqDTO.price())
                .quantity(productCreateReqDTO.quantity())
                .image(productCreateReqDTO.image())
                .rating(productCreateReqDTO.rating())
                .build();
    }
    public static ProductResDTO.ProductDetailResDTO toProductDetailResponse(Product product) {
        return ProductResDTO.ProductDetailResDTO.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .rating(product.getRating())
                .build();
    }

    public static ProductResDTO.ProductCreateResDTO toProductResponse(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .productId(product.getId())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
