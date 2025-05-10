package com.project.likelion13thbe.domain.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ProductRequestDTO {

    @Getter
    public static class ProductListRequestDTO {
        @Schema(description = "상품 목록")
        private List<ProductRequestDTO.ProductReqDTO> products;
    }


    @Builder
    public record ProductCreateRequestDTO(
            String name,
            String content,
            Long price,
            String image,
            Long quantity,

            Long memberId
    ) {
    }

    @Getter
    public static class ProductReqDTO {
        @Schema(description = "상품 설명",example = "상품에 대한 설명")
        private String content;
    }
}

