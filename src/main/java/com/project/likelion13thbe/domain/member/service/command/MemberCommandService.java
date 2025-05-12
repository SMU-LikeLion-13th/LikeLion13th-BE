package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO);

    void updatePassword(Long email, MemberReqDTO.PasswordResetDTO dto);

    void deleteMember(Long userId);
}
