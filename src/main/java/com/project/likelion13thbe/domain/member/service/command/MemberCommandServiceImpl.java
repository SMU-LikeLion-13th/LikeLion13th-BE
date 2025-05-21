package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.convert.MemberConvert;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.SignUpRequest signUpRequest){
        Member member= MemberConvert.toMember(signUpRequest,passwordEncoder);

        memberRepository.save(member);

        return MemberConvert.toMemberResDTO(member);
    }
    @Override
    public void updatePassword(String email,MemberReqDTO.PasswordResetDTO passwordResetDTO){
        Member member=memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));
        String encodedPassword = passwordEncoder.encode(passwordResetDTO.password());
        member.updatePassword(encodedPassword);
    }
    @Override
    public void deleteMember(String email){
        Member member=memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));
        member.delete();
    }



}
