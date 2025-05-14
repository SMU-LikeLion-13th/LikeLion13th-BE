package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.dto.ProductDetailDTO;
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
                .description(productCreateReqDTO.description())
                .image(productCreateReqDTO.image())
                .price(productCreateReqDTO.price())
                .productType(productCreateReqDTO.productType())
                .productQuantity(productCreateReqDTO.productQuantity())
                .member(member)
                .build();
    }

    public static ProductResDTO.ProductCreateResDTO toProductResDTO(Product product) {
        return ProductResDTO.ProductCreateResDTO.builder()
                .productId(product.getProductId())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static ProductResDTO.ProductDetailResDTO toProductDetailResDTO(Product product, Double ratingAvg, Integer reviewCount) {
        return ProductResDTO.ProductDetailResDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .productType(product.getProductType())
                .ratingAvg(ratingAvg)
                .reviewCount(reviewCount)
                .build();
    }

    public static ProductResDTO.ProductListResDTO toProductListResDTO(List<ProductResDTO.ProductDetailResDTO> productList) {
        return ProductResDTO.ProductListResDTO.builder()
                .productList(productList)
                .build();
    }

    public static ProductResDTO.ProductCursorResDTO toProductCursorResDTO(Slice<ProductDetailDTO> productDetailDTOSlice) {
        List<ProductResDTO.ProductDetailResDTO> productList = productDetailDTOSlice.stream()
                .map(productDetailDTO -> toProductDetailResDTO(
                        productDetailDTO.product(), productDetailDTO.ratingAvg(), productDetailDTO.reviewCount().intValue()))
                .toList();

        // 다음 cursor 지정
        Long nextCursor = null;
        if (!productDetailDTOSlice.isEmpty() && productDetailDTOSlice.hasNext()) {
            nextCursor = productDetailDTOSlice.getContent().get(productDetailDTOSlice.getNumberOfElements() - 1).product().getProductId();
        }

        return ProductResDTO.ProductCursorResDTO.builder()
                .products(productList)
                .hasNext(productDetailDTOSlice.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
