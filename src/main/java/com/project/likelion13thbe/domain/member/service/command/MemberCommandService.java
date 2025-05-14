package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
        Member member = memberRepository.findByMemberIdAndNotDeleted(passwordResetDTO.memberId()).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        //member.updatePassword(passwordEncoder.encode(passwordResetDTO.password()));

    }

    public void deleteMember(MemberReqDTO.MemberDeleteDTO memberDeleteDTO) {
        Member member = memberRepository.findByMemberIdAndNotDeleted(memberDeleteDTO.memberId()).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.delete();
    }


}
