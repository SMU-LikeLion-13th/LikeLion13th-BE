package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    // 사용자 회원가입 , 요청 DTO → Member 엔티티로 변환
    public static Member toMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        return Member.builder()
                .email(memberCreateRequestDTO.email())
                .password(memberCreateRequestDTO.password())
                .name(memberCreateRequestDTO.name())
                .image(memberCreateRequestDTO.image())
                .build();
    }

    // 사용자 회원가입 , 저장된 Member 엔티티 → 응답 DTO로 변환
    public static MemberResponseDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return MemberResponseDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
