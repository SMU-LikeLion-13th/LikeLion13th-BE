package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return Member.builder()
                .name(memberCreateReqDTO.name())
                .email(memberCreateReqDTO.email())
                .password(memberCreateReqDTO.password())
                .build();

    }

    public static MemberResDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }



}
