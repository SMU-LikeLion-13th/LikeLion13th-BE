package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
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
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    public MemberResponseDTO.MemberCreateResDTO createMember(MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        // DTO -> Member
        Member member = MemberConverter.toMember(memberCreateRequestDTO);

        // Member 엔티티 DB에 저장
        memberRepository.save(member);

        // 응답 DTO로 변환하고 return
        return MemberConverter.toMemberResponseDTO(member);
    }

    public MemberResponseDTO.MemberPreviewResDTO getMember() {
        // DB에서 pk가 1인 Member 조회
        Member member = memberRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return MemberConverter.toMemberPreviewResponseDTO(member);
    }

    public MemberResponseDTO.MemberOffsetResDTO getMemberOffset(Integer offset,Integer size) {
        Pageable pageable = PageRequest.of(offset-1, size);
        // Spring Data JPA의 페이지 번호는 0부터 시작하기 때문dp -1 해주기
        Page<Member> members = memberRepository.findAllByOrderByCreatedAtDesc(pageable);

        return MemberConverter.toMemberOffsetResponseDTO(members);
    }

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
