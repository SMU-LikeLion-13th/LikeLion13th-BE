package com.project.likelion13thbe.domain.review.dto.response;

import lombok.*;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewDTO (
            Long id,
            String content,
            Integer rate
    ){
    }
    @Builder
    public record ReviewListDTO (
            List<ReviewDTO> reviews
    ){
    }
}
