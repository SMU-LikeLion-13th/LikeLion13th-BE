package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO);

    MemberResDTO.ResetPasswordResDTO updatePassword(Long memberId, MemberReqDTO.ResetPasswordReqDTO dto);

    void deleteMember(Long memberId);

    void cleanupDeletedMembers();
}
