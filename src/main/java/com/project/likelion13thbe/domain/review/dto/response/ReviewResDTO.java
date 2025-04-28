package com.project.likelion13thbe.domain.review.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewDTO (
            Long id,
            String username,
            String profileImg,
            String content,
            int rate,
            LocalDateTime createAt
    ){
    }
    @Builder
    public record ReviewListDTO (
            List<ReviewDTO> reviews
    ){
    }
    @Builder
    public record UpdateReviewDTO (
            Long id,
            String username,
            String profileImg,
            String content,
            int rate,
            LocalDateTime updateAt){
    }
}
