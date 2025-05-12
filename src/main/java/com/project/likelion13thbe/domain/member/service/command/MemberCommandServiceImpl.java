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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.MemberCreateResDTO createMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        // DTO -> Member
        Member member = MemberConverter.toMember(memberCreateReqDTO);

        // Member 엔티티 DB에 저장

        // 이게 원래는 위에서 Member 객체를 생성할 때 검사를 해보려고 했는데,
        // 찾아보니 객체 생성 단에서만 검사를 하면 정말 희박한 확률로 동시에 가입을 하려고 할 때,
        // race condition 이슈가 발생할 수 있다고 해서 저장하는 그 순간에 try-catch 문을 작성했습니다.
        // 최종 방어선은 DB가 지켜야 하니까요 (생각해보면 DB는 동시성 제어가 되니 무조건 승리자가 정해집니다)
        // 그런데 실제로 회원가입을 했던 경험을 생각해보니 이메일 중복 검사를 하는데요
        // A의 이메일 중복 검사 승인 O (DB에 실제로 저장되진 않음) -> A가 회원가입 전체 폼 작성이 오래 걸렸음 + 아직 작성 중
        // -> B가 A와 동일한 이메일로 중복 검사 승인 (DB에 저장이 안된 값이니까 승인은 됨)
        // -> B가 A보다 폼을 먼저 작성함 -> A는 이메일 중복 검사를 받았음에도 폼을 다 작성하니 unique 조건 에러 발생
        // 회원 가입은 아무래도 시스템의 쾌적함을 위해서 병렬적으로 발생해야 한다고 생각하는데
        // 그러면 회원가입 폼을 작성하기 전에 중복 확인을 받는 단계에서 이 이메일이 지금 가입 중에 있다라는
        // 어느 정도 시간(20~30분)을 갖고 DB에 존재하게 임시 테이블을 작성하는 것도 방법일까요? 아니면 어떤 다른 방법이 있을까요?
        // 마치 KTX 예약 장바구니에 담아두면 30분 뒤에 자동으로 자리가 풀리는 느낌입니다
        // 지금 단계에서는 스웨거로 작성해서 병렬 요청이 불가하니 DB 단에서만 예외 처리를 했습니다

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
}
