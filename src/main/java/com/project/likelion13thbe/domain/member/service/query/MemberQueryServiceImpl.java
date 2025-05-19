package com.project.likelion13thbe.domain.member.service.query;

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
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResDTO.MemberDetailResDTO getMember(String email) {
        // DB에서 pk가 1인 Member 조회
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new RuntimeException("Member가 존재하지 않음"));

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberPreviewResDTO(member);
    }

    @Override
    public MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset - 1, size);
        Page<Member> members = memberRepository.findAllByOrderByCreatedAtDesc(pageable);

        return MemberConverter.toMemberOffsetResDTO(members);
    }

    @Override
    public MemberResDTO.MemberCursorResDTO getMemberCursor(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        // cursor가 0일 경우(첫페이지) cursor 최대값
        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Member> members = memberRepository.findAllByMemberIdLessThanOrderByMemberIdDesc(cursor, pageable);

        return MemberConverter.toMemberCursorResDTO(members);
    }

}
