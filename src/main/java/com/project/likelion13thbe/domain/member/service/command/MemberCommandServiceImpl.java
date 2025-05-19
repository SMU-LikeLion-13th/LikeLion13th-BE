package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        // DTO -> Member
        Member member = MemberConverter.toMember(
                memberCreateReqDTO,
                passwordEncoder.encode(memberCreateReqDTO.password())
        ); // 암호화 방식?

        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new MemberException(MemberErrorCode.MEMBER_EMAIL_DUPLICATE);
        }

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberResponseDTO(member);
    }

    @Override
    public void updatePassword(Long memberId, MemberReqDTO.PasswordResetDTO passwordResetDTO) {
        // 회원 정보 조회
        Member member = memberRepository.findByMemberIdAndNotDeleted(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.updatePassword(passwordResetDTO.password());
    }

    @Override
    public void deleteMember(Long memberId) {
        // 회원 정보 조회
        Member member = memberRepository.findByMemberIdAndNotDeleted(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // soft delete 처리
        member.delete();
    }

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional
    public void cleanupDeletedMember() {
        log.info("삭제된 멤버를 제거하는 스케쥴 시작");

        // 한 달 전 날짜 계산
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        // 한 달 전 이전에 소프트 딜리트된 회원 조회
        List<Member> membersToDelete = memberRepository.findDeletedMembersBefore(oneMonthAgo);

        if (membersToDelete.isEmpty()) {
            log.info("제거할 멤버가 없습니다.");
            return;
        }
        for (Member member : membersToDelete) {
            try {
                log.info("회원 삭제 시도: id={}, email={}", member.getMemberId(), member.getEmail());
                memberRepository.delete(member);
                log.info("회원 삭제 성공: id={}", member.getMemberId());
            } catch (Exception e) {
                // 연관 관계? CASCADE? 이슈
                log.error("회원 삭제 실패: id={}, 이유={}", member.getMemberId(), e.getMessage());
            }
        }
        log.info("삭제가 완료되었습니다.\n");
    }
}
