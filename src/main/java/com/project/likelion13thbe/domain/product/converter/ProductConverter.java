package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

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

    public static ProductResDTO.ProductPreviewResDTO toProductPreviewResDTO(Product product, Double scoreAVG, Integer reviewCount) {

        return ProductResDTO.ProductPreviewResDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .content(product.getContent())
                .image(product.getImage())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .scoreAvg(scoreAVG)
                .reviewCount(reviewCount)
                .createdAt(product.getCreatedAt())
                .build();
    }


    public static ProductResDTO.ProductPreviewResDTO toProductPreviewResponseDTO(Product product) {
        return ProductResDTO.ProductPreviewResDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .build();
    }

    public static ProductResDTO.ProductCursorResDTO toProductCursorResDTO(Slice<Product> products) {
        List<ProductResDTO.ProductPreviewResDTO> productList = products.stream()
                .map(ProductConverter::toProductPreviewResponseDTO)
                .toList();

        // 다음 cursor 지정
        Long nextCursor = null;
        if (!products.isEmpty() && products.hasNext()) {
            nextCursor = products.getContent().get(products.getNumberOfElements() - 1).getId();
        }

        return ProductResDTO.ProductCursorResDTO.builder()
                .products(productList) // `members` → `products` 변경
                .hasNext(products.hasNext())
                .nextCursor(nextCursor)
                .build();
    }


}
