package com.project.likelion13thbe.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberReqDTO {
    @Getter
    public static class MemberCreateReqDTO {
        private String email;
        private String password;
        private String nickname;
    }

    @Getter
    public static class MemberUpdateReqDTO {
        private String email;
        private String password;
        private String nickname;
    }

    // 로그인 요청 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginRequestDTO {
        public String email;
        public String password;
    }
}
