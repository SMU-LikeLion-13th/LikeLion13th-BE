package com.project.likelion13thbe.domain.review.dto.request;

import lombok.*;

public class ReviewReqDTO {

    @Builder
    public record ReviewCreateReqDTO(
            Long memberId,
            Double rating,
            String content
    ) {
    }

    @Builder
    public record ReviewUpdateReqDTO(
            Double rating,
            String content
    ) {
    }
}
