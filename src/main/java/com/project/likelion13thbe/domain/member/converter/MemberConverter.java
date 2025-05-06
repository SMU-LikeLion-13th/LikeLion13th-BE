package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        return Member.builder()
                .email(memberCreateRequestDTO.email())
                .password(memberCreateRequestDTO.password())
                .name(memberCreateRequestDTO.name())
                .profileImage(memberCreateRequestDTO.profileImage())
                .socialType(memberCreateRequestDTO.socialType())
                .build();
    }


    public static MemberResponseDTO.MemberCreateResponseDTO toMemberResponseDTO(Member member) {
        return MemberResponseDTO.MemberCreateResponseDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
