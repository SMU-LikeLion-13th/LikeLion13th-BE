package com.project.likelion13thbe.domain.member.dto.response;

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


}
