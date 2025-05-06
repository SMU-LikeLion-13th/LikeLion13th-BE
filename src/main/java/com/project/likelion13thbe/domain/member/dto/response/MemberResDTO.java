package com.project.likelion13thbe.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {
    public record MemberResponseDTO(
            Long id,
            String content
    ){
    }


    @Builder
    public record MemberCreateResDTO(
                    Long id,
                    LocalDateTime createdAt
            ){

    }

}
