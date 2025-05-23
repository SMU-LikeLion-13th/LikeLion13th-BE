package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import java.util.Optional;

public interface MemberQueryService {

    MemberResponseDTO.MemberPreviewResDTO getMember(Long userId);
    MemberResponseDTO.MemberOffsetResDTO getMemberOffset(Integer offset,Integer size);
    MemberResponseDTO.MemberCursorResDTO getMemberCursor(Long cursor,Integer size);
    Optional<Member> findByEmail(String emailInfo);
}
