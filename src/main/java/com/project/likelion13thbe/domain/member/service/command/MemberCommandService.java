package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberCommandService {
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO);

    public void updatePassword(Long memberId, MemberReqDTO.PasswordResetDTO passwordResetDTO);

    public void deleteMember(Long memberId);
}
