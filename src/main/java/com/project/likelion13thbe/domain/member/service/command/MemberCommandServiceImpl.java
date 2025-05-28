package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        Member member = MemberConverter.toMember(memberCreateReqDTO, passwordEncoder);

        memberRepository.save(member);

        return MemberConverter.toMemberResDTO(member);
    }

    public void deleteMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Member Not Found"));
        memberRepository.delete(member);
    }

    @Transactional
    public void updateMemberByEmail(String email, MemberReqDTO.MemberUpdateReqDTO dto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Member Not Found"));

        if (dto.getEmail() != null) {
            member.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            member.setPassword(dto.getPassword());
        }
        if (dto.getNickname() != null) {
            member.setNickname(dto.getNickname());
        }
        memberRepository.save(member);
    }

    public void updateMember(Long id, MemberReqDTO.MemberUpdateReqDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member Not Found"));

        if (dto.getEmail() != null) {
            member.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            member.setPassword(dto.getPassword());
        }
        if (dto.getNickname() != null) {
            member.setNickname(dto.getNickname());
        }
        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Member Not Found");
        }
    }
}
