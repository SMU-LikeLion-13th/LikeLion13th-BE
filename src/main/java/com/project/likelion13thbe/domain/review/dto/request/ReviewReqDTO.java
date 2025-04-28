package com.project.likelion13thbe.domain.review.dto.request;

import lombok.*;

public class ReviewReqDTO {

    @Builder
    public record CreateReviewDTO(
            String content,
            int rate
    ) {
    }
}

