package com.project.likelion13thbe.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {
    @Builder
    public record ReviewCreateReqDTO(
            String description,
            Integer rating,
            Long memberId

    ) {}
    @Getter
    public static class ReviewUpdateDTO{
        @Schema(description="리뷰 수정", example = "new리뷰")
        String description;
        Integer rating;

    }
}
