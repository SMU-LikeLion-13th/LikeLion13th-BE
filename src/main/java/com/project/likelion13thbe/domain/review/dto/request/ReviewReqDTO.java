package com.project.likelion13thbe.domain.review.dto.request;


public class ReviewReqDTO {

    public record ReviewCreateReqDTO(
            Long memberId,
            Double rating,
            String content
    ) {
    }

    public record ReviewUpdateReqDTO(
            Double rating,
            String content
    ) {
    }
}
