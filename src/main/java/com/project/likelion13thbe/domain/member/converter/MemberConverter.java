package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.type.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return Member.builder()
                .name(memberCreateReqDTO.name())
                .email(memberCreateReqDTO.email())
                .password(memberCreateReqDTO.password())
                .role(Role.USER)
                .build();

    }

    public static MemberResDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public  static MemberResDTO.MemberPreviewResDTO toMemberPreviewResponseDTO(Member member) {
        return MemberResDTO.MemberPreviewResDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    public static MemberResDTO.MemberOffsetResDTO toMemberOffsetResponseDTO(Page<Member> page) {

        List<MemberResDTO.MemberPreviewResDTO> members = page.getContent().stream()
                .map(MemberConverter::toMemberPreviewResponseDTO)
                .toList();

        return MemberResDTO.MemberOffsetResDTO.builder()
                .members(members)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
    public static MemberResDTO.MemberCursorResDTO toMemberCursorResDTO(Slice<Member> members) {
        List<MemberResDTO.MemberPreviewResDTO> memberLsit = members.stream()
                .map(MemberConverter::toMemberPreviewResponseDTO)
                .toList();

        // 다음 cursor 지정
        Long nextCursor = null;
        if (!members.isEmpty() && members.hasNext()) {
            nextCursor = members.getContent().get(members.getNumberOfElements() - 1).getId();
        }

        return MemberResDTO.MemberCursorResDTO.builder()
                .members(memberLsit)
                .hasNext(members.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
