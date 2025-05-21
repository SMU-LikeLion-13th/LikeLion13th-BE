package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServicelmpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {

        String encodedPassword = passwordEncoder.encode(memberCreateReqDTO.password());

        //DTO -> Member
        Member member = Member.builder()
                .email(memberCreateReqDTO.email())
                .password(encodedPassword) // 해싱된 비밀번호 저장
                .role(Role.USER)
                .build();

        // Member 엔티티 DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberResponseDTO(member);
    }

    @Override
    public void updatePassword(String email, MemberReqDTO.PasswordResetDTO dto){
        //회원정보 조회
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        //비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(dto.password());
        member.updatePassword(encodedPassword);
    }

    @Override
    public void deleteMember(String email){
        //회원 정보 조회
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        //soft delete 처리
        member.delete();
    }
}
