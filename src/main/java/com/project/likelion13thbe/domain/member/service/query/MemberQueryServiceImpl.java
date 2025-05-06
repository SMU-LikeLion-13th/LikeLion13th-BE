package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;


    @Override
    public MemberResDTO.MemberPreviewResDTO getMember() {
        // DB에서 pk가 1인 Member 조회
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원이 존재하지 않습니다."));
        return MemberConverter.toMemberPreviewResponseDTO(member);
    }

    public MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset,Integer size) {
        Pageable pageable = PageRequest.of(offset-1, size);

        // Spring Data JPA의 페이지 번호는 0부터 시작하기 때문에 -1 해주기
        Page<Member> members = memberRepository.findAllByOrderByCreatedAtDesc(pageable);

        return MemberConverter.toMemberOffsetResponseDTO(members);
    }
}
