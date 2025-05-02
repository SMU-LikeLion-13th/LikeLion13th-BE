package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    public MemberResponseDTO.MemberCreateResDTO createMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        // DTO -> Member
        Member member = MemberConverter.toMember(memberCreateRequestDTO);

        // Member 엔티티 DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환하고 return
        return MemberConverter.toMemberResponseDTO(member);
    }
}
