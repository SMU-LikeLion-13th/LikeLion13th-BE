package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;

public interface MemberQueryService {

    MemberResponseDTO.MemberPreviewResponseDTO getMember();

    MemberResponseDTO.MemberOffsetResponseDTO getMemberOffset(Integer offset, Integer size);

    MemberResponseDTO.MemberCursorResponseDTO getMemberCursor(Long cursor, Integer size);

}
