package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface MemberCommandService {
    MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO);
    void updateMember(Long id, MemberReqDTO.MemberUpdateReqDTO dto);
    void deleteMember(Long id);
}
