package com.project.likelion13thbe.domain.review.dto.request;

import java.time.LocalDateTime;

public class CommentReqDTO {

    public record CommentCreateReqDTO(
            String content,
            LocalDateTime date,
            Long memberId,
            Long reviewId
    ) {}

}
