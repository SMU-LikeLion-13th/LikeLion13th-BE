package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.entity.CustomUserDetails;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Member", description = "유저 관련 API")
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "일반 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResponseDTO.JwtTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized 아이디나 비밀번호 오류",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<MemberResponseDTO.JwtTokenResponse> localLogin(@RequestBody MemberRequestDTO.LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Operation(summary = "비밀번호 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized\t\n 1. jwt 유효하지 않음 \t\n 2. 비밀번호 유형 맞지 않음",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/password-reset/{email}")
    public CustomResponse<String> resetPassword(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody MemberRequestDTO.ResetPasswordRequestDTO resetPasswordRequestDTO) {
        String email = userDetails.getMember().getEmail();
        memberCommandService.updatePassword(email, resetPasswordRequestDTO);
        return CustomResponse.onSuccess("비밀번호 변경 성공");
    }

    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Conflict, 중복된 이메일",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/signup")
    public CustomResponse<MemberResponseDTO.MemberCreateResponseDTO> localSignUp(
            @RequestBody MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        return CustomResponse.onSuccess(HttpStatus.CREATED, memberCommandService.createMember(memberCreateRequestDTO));
    }

    @Operation(summary = "내 리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class))),})
    @GetMapping("/my/reviews")
    public CustomResponse<ReviewResponseDTO.ReviewListResponseDTO> getMyReviews(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getMember().getId();
        return CustomResponse.onSuccess(HttpStatus.OK, reviewQueryService.getReviewsByMemberId(memberId));
    }

    @Operation(summary = "카카오 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResponseDTO.JwtTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "카카오 토큰 발급 실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/kakao/login")
    public ResponseEntity<MemberResponseDTO.JwtTokenResponse> kakaoLogin(@RequestBody MemberRequestDTO.KakaoLoginRequestDTO kakaoLoginRequestDTO) {
        return null;
    }

    @GetMapping("/{memberId}")
    public CustomResponse<MemberResponseDTO.MemberPreviewResponseDTO> getMember(@PathVariable Long memberId) {
        return CustomResponse.onSuccess(memberQueryService.getMember(memberId));
    }

    @GetMapping("/offset")
    public CustomResponse<MemberResponseDTO.MemberOffsetResponseDTO> getMemberOffset(
            @RequestParam Integer offset, @RequestParam Integer size) {
        return CustomResponse.onSuccess(memberQueryService.getMemberOffset(offset, size));
    }

    @GetMapping("/cursor")
    public CustomResponse<MemberResponseDTO.MemberCursorResponseDTO> getMemberCursor(
            @RequestParam Long cursor, @RequestParam Integer size
    ) {
        return CustomResponse.onSuccess(memberQueryService.getMemberCursor(cursor, size));
    }


    @DeleteMapping
    @Operation(summary = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")})
    public CustomResponse<String> deleteMember(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getMember().getId();
        memberCommandService.deleteMember(memberId);
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }
}