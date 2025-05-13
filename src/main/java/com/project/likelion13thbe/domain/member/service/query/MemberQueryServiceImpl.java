package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.convert.MemberConvert;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
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
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;

    @Override
    public MemberResDTO.MemberPreviewResDTO getMember(Long userId){
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_ERROR_CODE));
        return MemberConvert.toMemberPreviewResDTO(member);
    }
    @Override
    public MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size){
        Pageable pageable = PageRequest.of(offset-1, size);
        Page<Member> members=memberRepository.findAllByOrderByIdDesc(pageable);

        return MemberConvert.toMemberOffsetResDTO(members);
    }
    @Override
    public MemberResDTO.MemberCursorResDTO getMemberCursor(Long cursor, Integer size) {
        Pageable pageable =PageRequest.of(0, size);

        // cursor가 0일 경우(첫페이지) cursor 최대값
        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Member> members = memberRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);

        return MemberConvert.toMemberCursorResDTO(members);
    }
}
