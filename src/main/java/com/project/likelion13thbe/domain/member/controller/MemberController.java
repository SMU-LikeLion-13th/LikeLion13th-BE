package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequiredArgsConstructor
@Tag(name="Member API", description = "멤버 관련 API입니다.")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public ResponseEntity<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember(MemberReqDTO.memberCreateReqDTO));
    }

    @PatchMapping("/members/password")
    @Operation(summary = "비밀번호 수정", description = "회원의 비밀번호를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    })
    public CustomResponse<String> resetPassword(
            @AuthenticationPrincipal Userdetails userdetails,
            @RequestBody MemberReqDTO.PasswordResetDTO request
    ) {
        memberCommandService.updatePassword(userDetails.getUserName(), request);
        return CustomResponse.onSuccess("비밀번호 변경 성공");
    }

    @DeleteMapping("/members/{memberId}")
    @Operation(summary = "회원 탈퇴", description = "회원 계정을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    })
    public CustomResponse<String> deleteMember(@AuthenticationPrincipal UserDetails userDetails) {
        memberCommandService.deleteMember(userDetails.getUsername());
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }
    // 카카오 로그인 OAuth 이거 어케 함?

    // 일반 로그인
    @Operation(summary = "일반 로그인")
    @Parameter(name = "userId", description = "Member PK", example = "1")
    @PostMapping("/api/v1/login/normal/users/{userId}")
    public MemberResDTO.MemberResponseDTO getUser(
            @PathVariable Long userId
    ) {
        return null;
    }

    // 회원가입
    @Operation(summary = "회원가입")
    @Parameter(name = "userId", description = "Member PK", example = "1")
    @PostMapping("/api/v1/register/users/{userId}")
    public MemberResDTO.MemberResponseDTO createUser(
            @PathVariable Long userId
    ) {
        return null;
    }

    // 비밀번호 수정
    @Operation(summary = "비밀번호 수정")
    @Parameter(name = "userId", description = "Member PK", example = "1")
    @PatchMapping("/api/v1/password/users/{userId}")
    public MemberResDTO.MemberResponseDTO resetPassword(
            @PathVariable Long userId
    ) {
        return null;
    }
}
