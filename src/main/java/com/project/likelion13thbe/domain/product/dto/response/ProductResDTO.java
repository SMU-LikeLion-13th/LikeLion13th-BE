package com.project.likelion13thbe.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

public class ProductResDTO {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ProductResponseDTO {
        private Long id;
        private String content;

        @Schema(description = "Product의 PK", example = "1")
        private Long productId;
    }

    @Getter
    @Builder
    public static class ProductCreateResDTO {
        private Long id;
        private LocalDateTime createdAt;
    }

    public record Test1DTO(
            Long id,
            String content
    ) {}
}
