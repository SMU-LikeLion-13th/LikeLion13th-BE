package com.project.likelion13thbe.domain.product.converter;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO, Member member) {
        return Product.builder()
                .title(productCreateReqDTO.title())
                .content(productCreateReqDTO.content())
                .price(productCreateReqDTO.price())
                .quantity(productCreateReqDTO.quantity())
                .image(productCreateReqDTO.Image())
                .member(member)
                .build();
    }

    public static ProductResDTO.ProductCreateResDTO toProductResponseDTO(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .build();

    }

    public static ProductResDTO.ProductPreviewResDTO toProductPreviewResDTO(Product product, Double ratingAvg, Long reviewCount) {

        return ProductResDTO.ProductPreviewResDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .content(product.getContent())
                .image(product.getImage())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .ratingAvg(ratingAvg)
                .reviewCount(reviewCount)
                .createdAt(product.getCreatedAt())
                .build();
    }
    public static ProductResDTO.ProductListResDTO toProductPreviewResponseDTOList(List<ProductResDTO.ProductPreviewResDTO> productPreviewResDTOList) {
        return ProductResDTO.ProductListResDTO.builder()
                .productList(productPreviewResDTOList)
                .build();
    }
}
