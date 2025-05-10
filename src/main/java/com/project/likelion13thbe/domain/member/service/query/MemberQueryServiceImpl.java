package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDTO.MemberPreviewResponseDTO getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 응답 DTO로 변환
        return MemberConverter.toMemberPreviewResponseDTO(member);

    }

    @Override
    public MemberResponseDTO.MemberOffsetResponseDTO getMemberOffset(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset - 1, size);

        Page<Member> members = memberRepository.findAllByOrderByCreatedAtDesc(pageable);

        return MemberConverter.toMemberOffsetResponseDTO(members);
    }

    @Override
    public MemberResponseDTO.MemberCursorResponseDTO getMemberCursor(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        // cursor가 0일 경우(첫페이지) cursor 최대값
        Slice<Member> members = memberRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);

        return MemberConverter.toMemberCursorResponseDTO(members);
    }
}