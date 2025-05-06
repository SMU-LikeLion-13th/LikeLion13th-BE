package com.project.likelion13thbe.domain.review.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record ReviewCreateReqDTO(
            String content,
            Double score,
            Long memberId
    ) {
    }


}
