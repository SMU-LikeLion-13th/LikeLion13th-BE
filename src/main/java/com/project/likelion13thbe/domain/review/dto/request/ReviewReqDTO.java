package com.project.likelion13thbe.domain.review.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewReqDTO {

    public record ReviewCreateReqDTO(
            String content,
            LocalDateTime date,
            Long memberId,
            Long productId
    ) {}
}
