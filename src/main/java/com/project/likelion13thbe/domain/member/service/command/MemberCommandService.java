package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.Exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.Exception.MemberException;
import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {
    private final MemberRepository memberRepository;

    //private final BCryptPasswordEncoder passwordEncoder;

    public MemberResDTO.MemberCreateResDTO createMember (MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {

        Member member = MemberConverter.toMember(memberCreateReqDTO);

        memberRepository.save(member);

        return MemberConverter.toMemberResponseDTO(member);
    }

    //@Override
    public void updatePassword(MemberReqDTO.PasswordResetDTO passwordResetDTO) {
        Member member = memberRepository.findByUserIdAndNotDeleted(passwordResetDTO.userId()).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        //member.updatePassword(passwordEncoder.encode(passwordResetDTO.password()));

    }


}
