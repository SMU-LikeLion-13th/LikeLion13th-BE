package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;

public class ProductConverter {
    public static Product toProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return Product.builder()
                .name(productCreateReqDTO.getName())
                .description(productCreateReqDTO.getDescription())
                .price(productCreateReqDTO.getPrice())
                .stock(productCreateReqDTO.getStock())
                .build();
    }

    public static ProductResDTO.ProductCreateResDTO toProductResDTO(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static ProductResDTO.ProductDTO toDTO(Product product) {
        return ProductResDTO.ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .rating(product.getRating())
                .build();
    }
}
