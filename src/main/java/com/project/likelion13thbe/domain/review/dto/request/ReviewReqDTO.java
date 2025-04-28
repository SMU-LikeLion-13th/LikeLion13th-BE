package com.project.likelion13thbe.domain.review.dto.request;

import lombok.*;

public class ReviewReqDTO {

    @Builder
    public record ReviewCreateReqDTO(
            Integer rating,
            String content
    ) {
    }

    @Builder
    public record ReviewUpdateReqDTO(
            Integer rating,
            String content
    ) {
    }
}
