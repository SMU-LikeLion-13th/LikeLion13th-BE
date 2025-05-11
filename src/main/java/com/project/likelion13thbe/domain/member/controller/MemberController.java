package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
@Tag(name="Member", description = "Member 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    @Operation(description = "회원가입")
    @PostMapping
    public CustomResponse<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody @Valid MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return CustomResponse.onSuccess(memberCommandService.createMember(memberCreateReqDTO));
    }

    @Operation(description = "유저 조회")
    @GetMapping
    public CustomResponse<MemberResDTO.MemberPreviewResDTO> getMember() {
        return CustomResponse.onSuccess(memberQueryService.getMember());
    }


    @Operation(description = "비밀번호 수정")
    @PatchMapping("/{memberId}/reset-password")
    public CustomResponse<MemberResDTO.ResetPasswordResDTO> resetPassword(
            @RequestBody @Valid MemberReqDTO.ResetPasswordReqDTO resetPasswordReqDTO,
            @PathVariable("memberId") @NotNull Long memberId
    ) {
        return CustomResponse.onSuccess(memberCommandService.updatePassword(memberId, resetPasswordReqDTO));
    }

    // 로그인은 반환값으로 토큰을 발급해야해서 일단 커스텀적용 안했습니다
    @Operation(description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> login(
            @RequestBody MemberReqDTO.LoginReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "카카오 로그인")
    @PostMapping("/login/kakao")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> kakaoLogin(
            @RequestBody MemberReqDTO.KakaoLoginRequestDTO dto
    ) {
        return ResponseEntity.ok(null);
    }
    @Operation(description = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    public CustomResponse<String> deleteMember(@PathVariable("memberId") @NotNull Long memberId) {
        memberCommandService.deleteMember((memberId));
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }
}
