package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {
    public record MemberResponseDTO(
            Long id,
            String content
    ) {}

    public record MemberCreateResDTO (
            Long id,
            LocalDateTime createdAt
    ) {}

    public record MemberPreviewResDTO(
            Long id,
            String email,
            String name
    ) {}

    public record MemberOffsetResDTO(
            List<MemberPreviewResDTO> members,
            Long totalElements,
            Integer totalPages
    ) {}

    public record MemberCursorResDTO(
            List<MemberPreviewResDTO> members,
            Long nextCursor,
            Boolean hasNext
    ) {}

    public record MemberPatchPasswordResDTO (
            Long id,
            LocalDateTime updatedAt
    ) {}
}
