package com.project.likelion13thbe.domain.review.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class ReviewResDTO {

    @Builder
    public record ReviewDetailResDTO(
            Long reviewId,
            Double rating,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String nickname,
            String profileImage
    ) {
    }

}
