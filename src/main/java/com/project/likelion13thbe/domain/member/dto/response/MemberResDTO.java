package com.project.likelion13thbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {
    public record MemberResponseDTO(){
    }

    @Builder
    public record MemberCreateResDTO(
                    Long id,
                    LocalDateTime createdAt
            ){

    }

    @Builder
    public record MemberPreviewResDTO(
            Long id,
            String email,
            String name
    ){
    }

    @Builder
    public record MemberOffsetResDTO(
            List<MemberPreviewResDTO> members,
            Long totalElements,
            Integer totalPages
    ){
    }

    @Builder
    public record MemberCursorResDTO(
            List<MemberPreviewResDTO> members,
            Long nextCursor,
            Boolean hasNext
    ){
    }


}
