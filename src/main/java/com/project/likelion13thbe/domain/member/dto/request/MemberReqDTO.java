package com.project.likelion13thbe.domain.member.dto.request;

import lombok.Getter;

public class MemberReqDTO {

    public record MemberCreateReqDTO(
            String email,
            String name,
            Long password
    ) {}


};
