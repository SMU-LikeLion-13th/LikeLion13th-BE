package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;

public interface MemberCommandService {
    void updatePassword(Long id, MemberRequestDTO.PasswordResetDTO dto);

    MemberResponseDTO.MemberCreateResDTO createMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO);

    void deleteMember(Long id);
}
