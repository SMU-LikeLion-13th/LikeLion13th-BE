package com.project.likelion13thbe.domain.review.dto.request;

import lombok.Builder;

public class ReviewRequestDTO {

    @Builder
    public record ReviewCreateRequestDTO(
            String content,
            Integer rating,
            String image,

            Long memberId
    ) {
    }

    @Builder
    public record ReviewUpdateRequestDTO(
            String content,
            Integer rating,
            String image
    ) {
    }
}
