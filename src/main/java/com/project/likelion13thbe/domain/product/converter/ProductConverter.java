package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct(ProductRequestDTO.ProductCreateRequestDTO productCreateRequestDTO) {
        return Product.builder()
                .name(productCreateRequestDTO.name())
                .content(productCreateRequestDTO.description())
                .price(productCreateRequestDTO.price())
                .image(productCreateRequestDTO.image())
                .build();
    }

    public static ProductResponseDTO.ProductCreateResponseDTO toProductResponseDTO(Product product) {
        return ProductResponseDTO.ProductCreateResponseDTO.builder()
                .productId(product.getId())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static ProductResponseDTO.ProductPreviewResDTO toProductPreviewResponseDTO(Product product) {
        return ProductResponseDTO.ProductPreviewResDTO.builder()
                .productId(product.getId())
                .name(product.getName())
                .content(product.getContent())
                .build();
    }

    public static ProductResponseDTO.ProductResDTO toProductResDTO(Product product) {
        return ProductResponseDTO.ProductResDTO.builder()
                .productId(product.getId())
                .content(product.getContent())
                .build();
    }

    public static ProductResponseDTO.ProductListResponseDTO toProductListResponseDTO(List<Product> products) {
        List<ProductResponseDTO.ProductResDTO> productResDTOs = products.stream()
                .map(ProductConverter::toProductResDTO)
                .toList();

        return ProductResponseDTO.ProductListResponseDTO.builder()
                .products(productResDTOs)
                .build();
    }

}
