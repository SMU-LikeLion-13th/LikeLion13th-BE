package com.project.likelion13thbe.domain.review.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewResDTO {

    @Builder
    public record ReviewDetailResDTO(
            Long reviewId,
            String description,
            String image,
            Integer rating,
            String name,
            String profileImage,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewListResDTO(
            List<ReviewDetailResDTO> reviewList
    ) {
    }

    @Builder
    public record ReviewCreateResDTO(
            Long reviewId
    ) {
    }


}
