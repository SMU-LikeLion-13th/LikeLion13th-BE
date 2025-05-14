package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDTO.MemberCreateResponseDTO createMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        // DTO -> Member
        Member member = MemberConverter.toMember(memberCreateRequestDTO);

        // Member Entity DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberResponseDTO(member);
    }

    @Override
    public void updatePassword(String email, MemberRequestDTO.ResetPasswordRequestDTO passwordResetRequestDTO) {
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.updatePassword(passwordResetRequestDTO.password());
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findByIdAndNotDeleted(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.delete();
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void cleanupDeletedMembers() {
        log.info("Starting scheduled cleanup deleted Members");

        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        List<Member> membersToDelete = memberRepository.findDeletedMembersBefore(oneMonthAgo);

        if (membersToDelete.isEmpty()) {
            log.info("No members to delete");
            return;
        }

        memberRepository.deleteAll(membersToDelete);

        log.info("Deleted {} members", membersToDelete.size());

    }

}
