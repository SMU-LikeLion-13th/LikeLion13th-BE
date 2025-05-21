package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberQueryService {

    MemberResDTO.MemberPreviewResDTO getMember(String email);

    MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size);

    //커서기반 사용자 조회
    MemberResDTO.MemberCursorResDTO getMemberCursor(Long cursor, Integer size);
}
