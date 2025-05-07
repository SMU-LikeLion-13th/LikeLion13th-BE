package com.project.likelion13thbe.domain.member.dto.request;

import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class MemberCreateReqDTO {
        private String email;
        private String password;
        private String nickname;
    }
}
