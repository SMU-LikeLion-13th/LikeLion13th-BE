package com.project.likelion13thbe.domain.review.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }
    @Builder
    public record ReviewPreviewResDTO(
            Long id,
            String username,
            String profileImg,
            String content,
            Double rate,
            LocalDateTime createdAt
    ){
    }
    @Builder
    public record ReviewListDTO (
            List<ReviewPreviewResDTO> reviews
    ){
    }
}
