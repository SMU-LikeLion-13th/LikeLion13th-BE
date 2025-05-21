package com.project.likelion13thbe.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class ReviewReqDTO {

    @Builder
    public record ReviewCreateReqDTO(
            String content,
            @NotNull(message = "평점은 필수 입력값입니다.")
            @Max(value = 5, message = "평점은 최대 5점 이하여야 합니다.")
            Double rate
    ) {
    }
    @Builder
    public record ReviewUpdateReqDTO(
            String content,
            @NotNull(message = "평점은 필수 입력값입니다.")
            @Max(value = 5, message = "평점은 최대 5점 이하여야 합니다.")
            Double rate){
    }
}

