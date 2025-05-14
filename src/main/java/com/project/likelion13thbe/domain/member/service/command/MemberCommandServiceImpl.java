package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDTO.MemberCreateResDTO createMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        // DTO -> Member
        Member member = MemberConverter.toMember(memberCreateRequestDTO);

        // Member 엔티티 DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환하고 return
        return MemberConverter.toMemberResponseDTO(member);
    }

    @Override
    public void updatePassword(Long id, MemberRequestDTO.PasswordResetDTO dto) {
        // 회원 정보 조회
        Member member = memberRepository.findByIdNotDeleted(id).orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.updatePassword(dto.getPassword());
    }

    @Override
    public void deleteMember(Long id) {
        // 회원 정보 조회
        Member member = memberRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
