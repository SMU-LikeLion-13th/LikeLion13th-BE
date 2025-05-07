package com.project.likelion13thbe.domain.member.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {
    @Builder
    public record JwtTokenResponse(
            String accessToken,
            String refreshToken
    ) {}


    @Builder
    public  record MemberCreateResDTO(
            Long id,
            LocalDateTime createAt
    ){}

    @Builder
    @AllArgsConstructor
    public static class MemberPreviewResDTO {
         Long id;
         String email;
         Integer age;
         String name;
         String password;
         String image;

    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MemberOffsetResDTO {
        private List<MemberPreviewResDTO> members;
        private  Long totalElements;
        private  Integer totalPages;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MemberCursorResDTO{
        private List<MemberResDTO.MemberPreviewResDTO> members;
        private Long nextCursor;
        private Boolean hasNext;
    }
}
