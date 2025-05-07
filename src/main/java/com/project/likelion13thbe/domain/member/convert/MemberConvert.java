package com.project.likelion13thbe.domain.member.convert;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConvert {
    public static Member toMember(MemberReqDTO.SignUpRequest signUpRequest){
        return Member.builder()
                .name(signUpRequest.name())
                .email(signUpRequest.email())
                .password(signUpRequest.password())
                .image(signUpRequest.image())
                .build();

    }
    public static MemberResDTO.MemberCreateResDTO toMemberResDTO(Member member){
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getId())
                .createAt(member.getCreatedAt())
                .build();

    }
    public static MemberResDTO.MemberPreviewResDTO toMemberPreviewResDTO(Member member){
        return MemberResDTO.MemberPreviewResDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .image(member.getImage())
                .build();
    }
    public static MemberResDTO.MemberOffsetResDTO toMemberOffsetResDTO(Page<Member> page){
        List<MemberResDTO.MemberPreviewResDTO> members=
                page.getContent().stream()
                        .map(MemberConvert::toMemberPreviewResDTO)
                        .toList();
        return MemberResDTO.MemberOffsetResDTO.builder()
                .members(members)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
    public static MemberResDTO.MemberCursorResDTO toMemberCursorResDTO(Slice<Member> members) {
        List<MemberResDTO.MemberPreviewResDTO> memberList = members.stream()
                .map(MemberConvert::toMemberPreviewResDTO)
                .toList();

        // 다음 cursor 지정
        Long nextCursor = null;
        if (!members.isEmpty() && members.hasNext()) {
            nextCursor = members.getContent().get(members.getNumberOfElements() - 1).getId();
        }

        return MemberResDTO.MemberCursorResDTO.builder()
                .members(memberList)
                .hasNext(members.hasNext())
                .nextCursor(nextCursor)
                .build();
    }


}
