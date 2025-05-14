package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct(ProductRequestDTO.ProductCreateRequestDTO productCreateRequestDTO, Member member) {
        return Product.builder()
                .name(productCreateRequestDTO.name())
                .description(productCreateRequestDTO.description())
                .price(productCreateRequestDTO.price())
                .quantity(productCreateRequestDTO.productQuantity())
                .image(productCreateRequestDTO.productImage())
                .productType(productCreateRequestDTO.productType())
                .member(member)
                .build();
    }

    public static ProductResponseDTO.ProductCreateResponseDTO toProductResponseDTO(Product product) {
        return ProductResponseDTO.ProductCreateResponseDTO.builder()
                .productId(product.getId())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static ProductResponseDTO.ProductDetailResponseDTO toProductDetailResponseDTO(Product product) {
        return ProductResponseDTO.ProductDetailResponseDTO.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .productType(product.getProductType())
                .ratingAvg(product.getRatingAvg())
                .build();
    }

    public static ProductResponseDTO.ProductCursorResponseDTO toProductCursorResponseDTO(Slice<Product> products) {
        List<ProductResponseDTO.ProductDetailResponseDTO> productList = products.stream()
                .map(ProductConverter::toProductDetailResponseDTO)
                .toList();

        Long nextCursor = null;
        if (!products.isEmpty() && products.hasNext()) {
            nextCursor = products.getContent().get(products.getNumberOfElements() - 1).getId();
        }

        return ProductResponseDTO.ProductCursorResponseDTO.builder()
                .products(productList)
                .hasNext(products.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
