package com.project.likelion13thbe.domain.member.service.command;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.SignUpRequest signUpRequest);

    void updatePassword(String email, MemberReqDTO.PasswordResetDTO passwordResetDTO);

    void deleteMember(String email);
}
