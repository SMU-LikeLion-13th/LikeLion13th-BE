package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.MemberPreviewResDTO getMember(Long memberId);

    MemberResDTO.MemberOffsetResDTO getMemberOffset(Integer offset, Integer size);
}
