package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.MemberPreviewResDTO getMember(Long userId);

    MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size);

    MemberResDTO.MemberCursorResDTO getMemberCursor(Long cursor, Integer size);
}
