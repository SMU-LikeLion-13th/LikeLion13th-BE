package com.project.likelion13thbe.domain.review.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewDTO (
    public record ReviewCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }
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
}
