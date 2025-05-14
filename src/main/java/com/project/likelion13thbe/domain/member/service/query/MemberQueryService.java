package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MemberResDTO.MemberPreviewResDTO getMember() {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMemberPreviewResponseDTO(member);
    }

    public MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset -1, size);

        Page<Member> members = memberRepository.findAllByOrderByCreatedAtDesc(pageable);

        return MemberConverter.toMemberOffsetResponseDTO(members);
    }
    public MemberResDTO.MemberCursorResDTO getMemberCursor(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Member> members = memberRepository.findAllByMemberIdLessThanOrderByMemberIdDesc(cursor, pageable);

        return MemberConverter.toMemberCursorResDTO(members);
    }

}
