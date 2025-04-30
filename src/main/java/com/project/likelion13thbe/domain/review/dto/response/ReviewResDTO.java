package com.project.likelion13thbe.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
public class ReviewResDTO {
    public record Test1DTO(
            Long id,
            String content
    ) {
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public class ReviewResponseDTO {
        private Long id;
        private String content;

        @Schema(description = "Review의 PK", example = "1")
        private Long reviewId;
        @Schema(description = "Product의 PK", example = "1")
        private Long productId;
    }
}
