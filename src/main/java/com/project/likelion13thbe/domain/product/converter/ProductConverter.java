package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {
    public static Product toProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO, Member member) {
        return Product.builder()
                .name(productCreateReqDTO.name())
                .content(productCreateReqDTO.content())
                .price(productCreateReqDTO.price())
                .quantity(productCreateReqDTO.quantity())
                .image(productCreateReqDTO.image())
                .member(member)
                .build();
    }

    public static ProductResDTO.ProductCreateResDTO toProductResponseDTO(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .build();

    }
}
