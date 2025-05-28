package com.project.likelion13thbe.global.Security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    // 로그인 성공 -> 토큰 응답
    public String accessToken; // 엑세스 토큰
    public String refreshToken; // 리프레쉬 토큰
}
