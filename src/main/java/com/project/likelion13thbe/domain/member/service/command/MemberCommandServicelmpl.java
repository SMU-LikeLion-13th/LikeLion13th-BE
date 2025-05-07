package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServicelmpl implements MemberCommandService{
    private final MemberRepository memberRepository;

    @Override
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        //DTO -> Member
        Member member = MemberConverter.toMember(memberCreateReqDTO);

        // Member 엔티티 DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberResponseDTO(member);
    }
}
