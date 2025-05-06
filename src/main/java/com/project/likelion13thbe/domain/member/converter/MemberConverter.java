package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.global.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberResDTO.MemberCreateDTO memberCreateReqDTO) {
        return null;
    }

    public static MemberResDTO.MemberCreateDTO toMemberCreateDTO(Member member) {
        return null;
    }



}
