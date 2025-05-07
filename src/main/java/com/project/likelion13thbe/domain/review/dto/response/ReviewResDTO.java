package com.project.likelion13thbe.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewResDTO {

    public record ReviewResponseDTO(
            Long id,
            String content
    ) {}

    @Builder
    public record ReviewCreateResDTO (
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    public record ReviewPreviewResDTO (
            String content,
            LocalDateTime date,
            Long userId,
            Long productId
    ) {}

    public record ReviewOffsetResDTO(
            List<ReviewPreviewResDTO> reviews,
            Long totalElements,
            Integer totalPages
    ) {}

    public record ReviewCursorResDTO(
            List<ReviewPreviewResDTO> reviews,
            Long nextCursor,
            Boolean hasNext
    ) {}
}
