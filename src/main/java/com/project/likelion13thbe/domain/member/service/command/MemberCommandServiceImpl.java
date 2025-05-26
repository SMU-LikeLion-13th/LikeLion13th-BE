package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.IsTempPassword;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import com.project.likelion13thbe.global.auth.email.EmailService;
import com.project.likelion13thbe.global.security.Config.SecurityConfig;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final SecurityConfig securityConfig;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        String password = memberCreateReqDTO.password();
        String encodedPassword = null;
        if (password != null) {
            encodedPassword = securityConfig.passwordEncoder().encode(password);
        }
        //DTO -> Member
        Member member = MemberConverter.toMember(memberCreateReqDTO, encodedPassword);

        // Member 엔티티 DB에 저장
        try {
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(MemberErrorCode.MEMBER_EMAIL_DUPLICATE);
        }

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberResponseDTO(member);
    }
    @Override
    public MemberResDTO.ResetPasswordResDTO updatePassword(String email, MemberReqDTO.ResetPasswordReqDTO resetPasswordReqDTO) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!securityConfig.passwordEncoder().matches(resetPasswordReqDTO.currentPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_WRONG_PASSWORD);
        }

        if (resetPasswordReqDTO.newPassword().equals(resetPasswordReqDTO.currentPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_SAME_PASSWORD);
        }

        String encodedNewPassword = securityConfig.passwordEncoder().encode(resetPasswordReqDTO.newPassword());
        member.updatePassword(encodedNewPassword);

        if (member.getIsTempPassword() == IsTempPassword.IS_TEMP_PASSWORD) {
            member.isNotTempPassword();
        }

        return MemberConverter.toMemberResetPasswordResponseDTO(resetPasswordReqDTO.newPassword(), resetPasswordReqDTO.currentPassword());
    }

    @Override
    public void deleteMember(String email) {
        Member member = memberRepository.findByEmail(email)
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

    @Override
    public void sendTempEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        String tempPassword = generateTempPassword();
        String encodedPassword = passwordEncoder.encode(tempPassword);

        member.updatePassword(encodedPassword);
        memberRepository.save(member);

        member.isTempPassword(); // isTempPassword = true;

        emailService.sendTempPassword(email, tempPassword);
    }
    //임시 비밀번호 발급용 메서드
    private String generateTempPassword() {
        return new SecureRandom().ints(10, 33, 122)
                .mapToObj(i -> (char) i)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }


}

