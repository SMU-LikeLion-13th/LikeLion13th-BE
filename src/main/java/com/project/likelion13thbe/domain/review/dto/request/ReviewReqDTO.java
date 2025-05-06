package com.project.likelion13thbe.domain.review.dto.request;

import lombok.Builder;

public class ReviewReqDTO {
    @Builder
    public record ReviewCreateReqDTO(
            String description,
            Integer rating,
            Long memberId

    ) {}
}
