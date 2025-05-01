package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberQueryService {
    public MemberResDTO.MemberPreviewResDTO getMember();

    public MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size);

    public MemberResDTO.MemberCursorResDTO getMemberCursor(Long cursor, Integer size);
}
