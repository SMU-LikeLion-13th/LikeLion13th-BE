package com.project.likelion13thbe.domain.review.dto.request;

import lombok.*;

public class ReviewReqDTO {

    @Builder
    public record ReviewCreateReqDTO(
            String content,
            double rate,
            Long memberId
    ) {
    }
    @Builder
    public record UpdateReviewDTO (
            String content,
            int rate){
    }
}

