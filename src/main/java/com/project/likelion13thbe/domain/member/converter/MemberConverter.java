package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.global.security.AuthType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return Member.builder()
                .email(memberCreateReqDTO.email())
                .password(memberCreateReqDTO.password())
                .name(memberCreateReqDTO.name())
                .build();
    }

    public static Member toEntity(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO,
                                  PasswordEncoder passwordEncoder) {
        String encodePassword = passwordEncoder.encode(memberCreateReqDTO.password());
        return Member.builder()
                .email(memberCreateReqDTO.email())
                .password(encodePassword)
                .active(true)
                .authType(AuthType.GENERAL)
                .build();

    }

    public static MemberResDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return new MemberResDTO.MemberCreateResDTO(
                member.getMemberId(),
                member.getCreatedAt()
        );
    }

    public static MemberResDTO.MemberPreviewResDTO toMemberPreviewResponseDTO(Member member) {
        return new MemberResDTO.MemberPreviewResDTO(
                member.getMemberId(),
                member.getEmail(),
                member.getName()
        );
    }

    public static MemberResDTO.MemberOffsetResDTO toMemberOffsetResponseDTO(Page<Member> page) {
        List<MemberResDTO.MemberPreviewResDTO> members =
                //page<Member>형의 페이지 결과 객체에서 실제 엔티티 리스트를 반환
                page.getContent()
                        //스트림으로 변환(함수형으로 가공할 수 있게)
                        .stream()
                        //toMemberOffsetResponseDTO 를 이용하여 해당 자료형을 convert
                        .map(MemberConverter::toMemberPreviewResponseDTO)
                        //리스트형으로 변환(DTO 리스트로 만들어짐)
                        .toList();
        return new MemberResDTO.MemberOffsetResDTO(
                members,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public static MemberResDTO.MemberCursorResDTO toMemberCursorResDTO(Slice<Member> members) {
        List<MemberResDTO.MemberPreviewResDTO> memberList = members.stream()
                .map(MemberConverter::toMemberPreviewResponseDTO)
                .toList();

        Long nextCursor = null;
        if (!members.isEmpty() && members.hasNext()) {
            nextCursor = members.getContent().get(members.getNumberOfElements() - 1).getMemberId();
        }

        boolean hasNext = members.hasNext();

        return new MemberResDTO.MemberCursorResDTO(
                memberList,
                nextCursor,
                hasNext
        );
    }

    public static MemberResDTO.MemberPatchPasswordResDTO toMemberPatchResDTO(Member member) {
        return  new MemberResDTO.MemberPatchPasswordResDTO(
                member.getMemberId(),
                member.getUpdatedAt()
        );
    }
}