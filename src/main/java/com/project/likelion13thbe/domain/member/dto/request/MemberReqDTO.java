package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;


public class MemberReqDTO {
    @Builder
    public record LoginRequest(String email, String password) {}


    @Builder
    public record  SignUpRequest(  //MemberCreateReqDTO
             String email,
             Integer age,
             String name,
             String password,
             String image,
             String role

            ) {}
    @Builder
    public record KakaoLoginRequest(
            String kakaoEmail, String kakaoPassword) {}
    @Builder
    public record PasswordResetDTO(
            String password
    ){
    }
}
