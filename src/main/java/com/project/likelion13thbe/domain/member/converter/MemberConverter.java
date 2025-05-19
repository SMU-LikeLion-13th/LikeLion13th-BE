package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {
    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return Member.builder()
                .name(memberCreateReqDTO.name())
                .email(memberCreateReqDTO.email())
                .password(encodedPassword)
                .role(Role.ROLE_USER)
                .build();
    }

    public static MemberResDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.MemberPreviewResDTO toMemberPreviewResponseDTO(Member member) {
        return MemberResDTO.MemberPreviewResDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    public static MemberResDTO.ResetPasswordResDTO toMemberResetPasswordResponseDTO(Member member, String currentPassword) {
        return MemberResDTO.ResetPasswordResDTO.builder()
                .currentPassword(currentPassword)
                .newPassword(member.getPassword())
                .build();
    }
}
