package com.project.likelion13thbe.domain.product.dto.response;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductListResponseDTO {
        @Schema(description = "상품 목록")
        private List<ProductResDTO> products;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductCreateResponseDTO {
        @Schema(description = "상품")
        private List<ProductResDTO> product;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductResDTO {
        @Schema(description = "product의 pk",example = "1")
        private Long productId;
        @Schema(description = "상품 설명", example = "~~한 상품입니다.")
        private String content;
    }


}
