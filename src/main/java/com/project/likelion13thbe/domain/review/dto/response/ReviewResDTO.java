package com.project.likelion13thbe.domain.review.dto.response;

import lombok.*;

public class ReviewResDTO {

    @Builder
    public record ReviewDTO (
            Long id,
            String content,
            Integer rate
    ){
    }
}
