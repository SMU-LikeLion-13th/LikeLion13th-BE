package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
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

    public MemberResDTO.MemberCreateDTO createMember(MemberResDTO.MemberCreateDTO memberCreateDTO) {
        Member member = MemberConverter.toMember(memberCreateDTO);

        memberRepository.save(member);

        return null;
    }
}
