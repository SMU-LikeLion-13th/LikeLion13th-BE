package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return Member.builder()
                .email(memberCreateReqDTO.getEmail())
                .password(memberCreateReqDTO.getPassword())
                .nickname(memberCreateReqDTO.getNickname())
                .build();
    }

    public static MemberResDTO.MemberCreateResDTO toMemberResDTO(Member member) {
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.MemberDTO toDTO(Member member) {
        return MemberResDTO.MemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();
    }
}
