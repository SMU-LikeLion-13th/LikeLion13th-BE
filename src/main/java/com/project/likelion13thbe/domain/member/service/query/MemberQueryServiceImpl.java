package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResDTO.MemberPreviewResDTO getMember() {
        //특정 유저 조회는 당장 토큰 추출이 불가해 1L로 했습니다!
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


        return MemberConverter.toMemberPreviewResponseDTO(member);
    }
}
