package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {
    public static Product toProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return Product.builder()
                .productId(productCreateReqDTO.productId())
                .name(productCreateReqDTO.name())
                .description(productCreateReqDTO.description())
                .image(productCreateReqDTO.image())
                .price(productCreateReqDTO.price())
                .productType(productCreateReqDTO.productType())
                .productQuantity(productCreateReqDTO.productQuantity())
                .build();
    }

    public static ProductResDTO.ProductCreateResDTO toProductResDTO(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .productId(product.getProductId())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static ProductResDTO.ProductDetailResDTO toProductDetailResDTO(Product product) {
        return ProductResDTO.ProductDetailResDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .productType(product.getProductType())
//                .ratingAvg()
//                .reviewCount()
                .build();
    }
}
