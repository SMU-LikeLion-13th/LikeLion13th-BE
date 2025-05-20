package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.Security.DTO.JwtDTO;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "카카오 로그인", description = "카카오 로그인을 수행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "카카오 로그인 성공")
    })
    @PostMapping("/login/kakao")
    public ResponseEntity<Void> kakaoLogin() {
        // 로그인 로직
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "일반 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtDTO.class)))    })
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> localLogin(@RequestBody MemberRequestDTO.LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호를 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    })
    @PostMapping("/password-reset")
    public CustomResponse<String> resetPassword(
            @RequestBody MemberRequestDTO.PasswordResetDTO requestDTO

    ) {
        memberCommandService.updatePassword(1L, requestDTO);
        // 비밀번호 수정 로직
        return CustomResponse.onSuccess("비밀번호 변경 성공");
    }

    @Operation(summary = "회원가입", description = "사용자 회원가입")
    @ApiResponse(responseCode = "201",
            description = "회원 생성 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberCreateResponseDTO.class)
            )
    )
    @PostMapping("/auth")
    public ResponseEntity<MemberResponseDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember((memberCreateRequestDTO)));
    }

    @Operation(summary = "사용자 정보 조회", description = "사용자 정보 조회")
    @ApiResponse(responseCode = "200",
            description = "사용자 정보 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberCreateResponseDTO.class)
            )
    )
    @GetMapping
    public ResponseEntity<MemberResponseDTO.MemberPreviewResDTO> getMember(
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        return ResponseEntity.ok(memberQueryService.getMember());
    }

    @Operation(summary = "4주차 실습", description = "사용자 정보 페이지네이션 조회_offset 기반")
    @ApiResponse(responseCode = "200",
            description = "사용자 정보 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberCreateResponseDTO.class)
            )
    )
    @GetMapping("/offset")
    public CustomResponse<MemberResponseDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return CustomResponse.onSuccess(memberQueryService.getMemberOffset(offset, size));
    }

    @DeleteMapping("/members/{memberId}")
    @Operation(summary = "회원 탈퇴", description = "회원 계정 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    })
    public CustomResponse<String> deleteMember(@PathVariable Long memberId) {
        memberCommandService.deleteMember(memberId);
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }
}
