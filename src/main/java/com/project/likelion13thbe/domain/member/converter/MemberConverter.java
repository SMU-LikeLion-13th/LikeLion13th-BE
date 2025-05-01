package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return Member.builder()
                .email(memberCreateReqDTO.email())
                .password(memberCreateReqDTO.password())
                .age(memberCreateReqDTO.age())
                .build();
    }

    public static MemberResDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

}
