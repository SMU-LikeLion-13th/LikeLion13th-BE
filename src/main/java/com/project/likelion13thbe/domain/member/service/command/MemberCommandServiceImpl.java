package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    @Override
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        if (memberRepository.existsByEmail(memberCreateReqDTO.email())) {
            throw new CustomException(MemberErrorCode.MEMBER_EMAIL_DUPLICATE);
        }
        //DTO -> Member
        Member member = MemberConverter.toMember(memberCreateReqDTO);

        // Member 엔티티 DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberResponseDTO(member);
    }
    @Override
    public MemberResDTO.ResetPasswordResDTO updatePassword(Long memberId, MemberReqDTO.ResetPasswordReqDTO resetPasswordReqDTO) {
        Member member = memberRepository.findByIdAndNotDeleted(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!member.getPassword().equals(resetPasswordReqDTO.currentPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_WRONG_PASSWORD);
        }

        member.updatePassword(resetPasswordReqDTO.password());
        return MemberConverter.toMemberResetPasswordResponseDTO(member, resetPasswordReqDTO.currentPassword());
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findByIdAndNotDeleted(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.delete();
    }

    @Override
    @Scheduled(cron = "0 0 3 * * *")
    public void cleanupDeletedMembers() {
        log.info("Starting scheduled cleanup of deleted members...");

        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        List<Member> membersToDelete = memberRepository.findDeletedMembersBefore(oneMonthAgo);

        for (Member member : membersToDelete) {
            try {
                // 물리적 삭제
                memberRepository.delete(member);
                log.info("Deleted member with ID: {}", member.getId());
            } catch (Exception e) {
                // 예외 발생 시 로그로 예외를 기록
                log.error("Error deleting member with ID: {}", member.getId(), e);
            }
        }
        log.info("Completed delete of deleted members.");
    }


}

