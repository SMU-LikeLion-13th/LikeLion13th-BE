package com.project.likelion13thbe.domain.member.converter;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.entity.MemberStatus;
import com.project.likelion13thbe.domain.member.entity.Role;
import com.project.likelion13thbe.domain.member.entity.SocialType;
import com.project.likelion13thbe.global.kakao.KakaoUserInfoResDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO, String password) {
        return Member.builder()
                .nickname(memberCreateReqDTO.nickname())
                .email(memberCreateReqDTO.email())
                .password(password)
                .role(Role.USER)
                .socialType(memberCreateReqDTO.socialType())
                .memberStatus(MemberStatus.NORMAL)
                .profileImage(memberCreateReqDTO.profileImage())
                .age(memberCreateReqDTO.age())
                .build();
    }


    // 일단 카카오 전용
    public static MemberReqDTO.MemberCreateReqDTO toMemberKakaoCreateReqDTO(KakaoUserInfoResDTO userInfo) {
        return MemberReqDTO.MemberCreateReqDTO.builder()
                .nickname(userInfo.kakaoAccount().profile().nickName())
                .email(userInfo.kakaoAccount().email())
//                .password(null)
//                .role(Role.USER)
                .socialType(SocialType.KAKAO)
                .build();
    }


    public static MemberResDTO.MemberCreateResDTO toMemberResponseDTO(Member member) {
        return MemberResDTO.MemberCreateResDTO.builder()
                .id(member.getMemberId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.MemberDetailResDTO toMemberPreviewResDTO(Member member) {
        return MemberResDTO.MemberDetailResDTO.builder()
                .id(member.getMemberId())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }

    public static MemberResDTO.MemberOffsetResDTO toMemberOffsetResDTO(Page<Member> page) {
        List<MemberResDTO.MemberDetailResDTO> members =
                page.getContent().stream()
                        .map(MemberConverter::toMemberPreviewResDTO)
                        .toList();

        return MemberResDTO.MemberOffsetResDTO.builder()
                .members(members)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public static MemberResDTO.MemberCursorResDTO toMemberCursorResDTO(Slice<Member> members) {
        List<MemberResDTO.MemberDetailResDTO> memberList = members.stream()
                .map(MemberConverter::toMemberPreviewResDTO)
                .toList();

        // 다음 cursor 지정
        Long nextCursor = null;
        if (!members.isEmpty() && members.hasNext()) {
            nextCursor = members.getContent().get(members.getNumberOfElements() - 1).getMemberId();
        }

        return MemberResDTO.MemberCursorResDTO.builder()
                .members(memberList)
                .hasNext(members.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
