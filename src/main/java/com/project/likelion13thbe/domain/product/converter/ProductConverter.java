package com.project.likelion13thbe.domain.product.converter;

import com.project.likelion13thbe.domain.order.entity.BaseEntity;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct (ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return Product.builder()
                .productName(productCreateReqDTO.productName())
                .price(productCreateReqDTO.price())
                .stars(productCreateReqDTO.stars())
                .build();
    }


    public static ProductResDTO.ProductCreateResDTO toProductCreateResDTO(Product product) {
        return new ProductResDTO.ProductCreateResDTO(
                product.getProductId(),
                product.getCreatedAt()
        );
    }

    public static ProductResDTO.ProductPreviewResDTO toProductPreviewResDTO(Product product) {
        return new ProductResDTO.ProductPreviewResDTO(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getStars()
        );
    }

    public static ProductResDTO.ProductOffsetResDTO toProductOffsetResDTO(Page<Product> page) {
        List<ProductResDTO.ProductPreviewResDTO> products =
                page.getContent().stream()
                        .map(ProductConverter::toProductPreviewResDTO)
                        .toList();
        return new ProductResDTO.ProductOffsetResDTO(
                products,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public static ProductResDTO.ProductCursorResDTO toProductCursorResDTO (Slice<Product> products) {
        List<ProductResDTO.ProductPreviewResDTO> productList = products.stream()
                .map(ProductConverter::toProductPreviewResDTO)
                .toList();

        Boolean hasNext = products.hasNext();

        Long nextCursor = null;
        if (!products.isEmpty() && products.hasNext()) {
            nextCursor = products.getContent().get(products.getNumberOfElements() -1).getProductId();
        }


        return new ProductResDTO.ProductCursorResDTO(
                productList,
                nextCursor,
                hasNext
        );
    }


}
