package com.project.likelion13thbe.domain.member.dto.request;

import lombok.Builder;

public class MemberReqDTO {
    @Builder
    public record LoginRequest(String email, String password) {}
    @Builder
    public record ResetPasswordRequest(String password) {}
    @Builder
    public record  SignUpRequest(
            String name,String email, String password,String profileImage) {}
    @Builder
    public record KakaoLoginRequest(
            String kakaoEmail, String kakaoPassword) {}
}
