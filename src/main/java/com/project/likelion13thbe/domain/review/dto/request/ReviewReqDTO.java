package com.project.likelion13thbe.domain.review.dto.request;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateReqDTO {
        private String content;
        private String starRating;
        private String likeCount;
    }
}
