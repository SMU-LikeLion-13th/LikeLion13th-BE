package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberQueryServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberResponseDTO.MemberPreviewResDTO getMember() {
        // DB에서 pk가 1인 Member 조회
        Member member = memberRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberPreviewResponseDTO(member);
    }

    @Override
    public MemberResponseDTO.MemberOffsetResDTO getMemberOffset(Integer offset,Integer size) {
        Pageable pageable = PageRequest.of(offset-1, size);
        // Spring Data JPA의 페이지 번호는 0부터 시작하기 때문dp -1 해주기
        Page<Member> members = memberRepository.findAllByOrderByCreatedAtDesc(pageable);

        return MemberConverter.toMemberOffsetResponseDTO(members);
    }

    @Override
    public MemberResponseDTO.MemberCursorResDTO getMemberCursor(Long cursor,Integer size)
    {
        Pageable pageable = PageRequest.of(0,size);

        // cursor가 0일 경우(첫 페이지)
        if (cursor  == 0){
            cursor = Long.MAX_VALUE;
        }

        Slice<Member> members = memberRepository.findAllByIdLessThanOrderByIdDesc(cursor,pageable);

        return MemberConverter.toMemberCursorResDTO(members);
    }
}
