package com.project.likelion13thbe.global.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SecurityDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginRequestDTO {
        public String email;
        public String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TokenDTO {
        public String accessToken;
        public String refreshToken;
    }

}
