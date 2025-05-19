package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(
            MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO,
            PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(memberCreateRequestDTO.password());
        return Member.builder()
                .email(memberCreateRequestDTO.email())
                .password(memberCreateRequestDTO.password())
                .name(memberCreateRequestDTO.name())
                .profileImage(memberCreateRequestDTO.profileImage())
                .socialType(memberCreateRequestDTO.socialType())
                .build();
    }


    public static MemberResponseDTO.MemberCreateResponseDTO toMemberResponseDTO(Member member) {
        return MemberResponseDTO.MemberCreateResponseDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MemberPreviewResponseDTO toMemberPreviewResponseDTO(Member member) {
        return MemberResponseDTO.MemberPreviewResponseDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .profileImage(member.getProfileImage())
                .build();
    }

    public static MemberResponseDTO.MemberOffsetResponseDTO toMemberOffsetResponseDTO(Page<Member> page) {
        List<MemberResponseDTO.MemberPreviewResponseDTO> members =
                page.getContent().stream().map(MemberConverter::toMemberPreviewResponseDTO).toList();

        return MemberResponseDTO.MemberOffsetResponseDTO.builder()
                .members(members)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public static MemberResponseDTO.MemberCursorResponseDTO toMemberCursorResponseDTO(Slice<Member> members) {
        List<MemberResponseDTO.MemberPreviewResponseDTO> memberList = members.stream()
                .map(MemberConverter::toMemberPreviewResponseDTO).toList();

        // 다음 커서 지정
        Long nextCursor = null;
        if (!members.isEmpty() && members.hasNext()) {
            nextCursor = members.getContent().get(members.getNumberOfElements() - 1).getId();
        }

        return MemberResponseDTO.MemberCursorResponseDTO.builder()
                .members(memberList)
                .hasNext(members.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}