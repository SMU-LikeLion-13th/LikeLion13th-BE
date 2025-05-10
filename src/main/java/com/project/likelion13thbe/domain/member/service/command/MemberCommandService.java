package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;

public interface MemberCommandService {

    MemberResponseDTO.MemberCreateResponseDTO createMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO);

    void updatePassword(String email, MemberRequestDTO.ResetPasswordRequestDTO passwordResetRequestDTO);
}
