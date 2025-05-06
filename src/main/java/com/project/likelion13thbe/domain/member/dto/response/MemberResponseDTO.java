package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {
    @Builder
    public record JwtTokenResponse(
            String accessToken,
            String refreshToken,
            Long expiresIn
    ) {
    }
}

    @Builder
    public record MemberCreateResponseDTO(
            Long id,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record MemberPreviewResponseDTO(
            Long id,
            String name,
            String email,
            String profileImage
    ) {
    }

    @Builder
    public record MemberOffsetResponseDTO(
            List<MemberPreviewResponseDTO> members,
            Long totalElements,
            Integer totalPages
    ) {
    }

    @Builder
    public record MemberCursorResponseDTO(
            List<MemberPreviewResponseDTO> members,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}