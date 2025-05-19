package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.dto.JwtDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name="Member", description = "Member 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    @Operation(description = "회원가입")
    @PostMapping("/auth")
    public CustomResponse<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody @Valid MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return CustomResponse.onSuccess(memberCommandService.createMember(memberCreateReqDTO));
    }

    @Operation(description = "유저 조회")
    @GetMapping
    public CustomResponse<MemberResDTO.MemberPreviewResDTO> getMember(
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        System.out.println("컨트롤러 진입");
        return CustomResponse.onSuccess(memberQueryService.getMember(userDetails.getUsername()));
    }


    @Operation(description = "비밀번호 수정")
    @PatchMapping("/reset-password")
    public CustomResponse<MemberResDTO.ResetPasswordResDTO> resetPassword(
            @RequestBody @Valid MemberReqDTO.ResetPasswordReqDTO resetPasswordReqDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        return CustomResponse.onSuccess(memberCommandService.updatePassword(email, resetPasswordReqDTO));
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
    public CustomResponse<String> deleteMember(@PathVariable("memberId") Long memberId) {
        memberCommandService.deleteMember((memberId));
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }
}
