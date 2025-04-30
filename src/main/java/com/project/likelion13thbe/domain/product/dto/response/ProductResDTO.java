package com.project.likelion13thbe.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class ProductResDTO {
    public record Test1DTO(
            Long id,
            String content
    ) {
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public class ProductResponseDTO {
        private Long id;
        private String content;

        @Schema(description = "Product의 PK", example = "1")
        private Long productId;
    }
}
