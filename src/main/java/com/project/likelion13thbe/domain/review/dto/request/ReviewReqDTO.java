package com.project.likelion13thbe.domain.review.dto.request;


import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record ReviewCreateReqDTO(
            Long memberId,
            @NotNull(message = "별점은 필수 입력값입니다.")
            Double rating,
            String content
    ) {
    }

    public record ReviewUpdateReqDTO(
            Double rating,
            String content
    ) {
    }
}
