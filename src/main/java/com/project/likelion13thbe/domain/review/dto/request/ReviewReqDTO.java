package com.project.likelion13thbe.domain.review.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewReqDTO {
    @Getter
    public static class ReviewCreateReqDTO {
        private String title;
        private String content;
        private Float rating;
        private Integer likeCount;

        // member 테이블 외래 PK
        private Long memberId;

        // product 테이블 외래 PK
        private Long productId;
    }

    @Getter
    public static class ReviewUpdateReqDTO {
        private String title;
        private String content;
        private Float rating;
        private Integer likeCount;

        private Long memberId;
        private Long productId;
    }
}
