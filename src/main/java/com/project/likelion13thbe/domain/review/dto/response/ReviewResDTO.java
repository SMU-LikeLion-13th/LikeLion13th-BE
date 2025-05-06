package com.project.likelion13thbe.domain.review.dto.response;


import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record ReviewResponseDTO(
            Long id,
            String content
    ){
    }

    public record ReviewListResponseDTO(
            List<ReviewResponseDTO> reviews
    ){
    }

    //리뷰 생성
    @Builder
    public record ReviewCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }
    public record ReviewUpdateDTO(
            Long id,
            String content
    ){
    }


}
