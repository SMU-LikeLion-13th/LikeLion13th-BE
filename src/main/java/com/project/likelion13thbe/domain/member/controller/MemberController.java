package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/members")
@Tag(name = "Member", description = "멤버 관련 API")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "createMember")
    @PostMapping("auth")
    public CustomResponse<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody @Valid MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return CustomResponse.onSuccess(memberCommandService.createMember(memberCreateReqDTO));
    }

    @Operation(summary = "로그인")
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody MemberReqDTO.LoginResDTO LoginResDTO) {
        return null;
    }

    @Operation(summary = "getMember")
    @GetMapping
    public ResponseEntity<MemberResDTO.MemberPreviewResDTO> getMember() {
        return ResponseEntity.ok(memberQueryService.getMember());
    }

    @Operation(summary = "비밀번호 수정")
    @PatchMapping("/{memberId}/password")
    public CustomResponse<String> resetPassword(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Valid MemberReqDTO.PasswordResetDTO passwordResetDTO
    ) {
        memberCommandService.updatePassword(memberId, passwordResetDTO);
        return CustomResponse.onSuccess("비밀번호 변경 성공");
    }

    // 회원 탈퇴 (JWT 인증 필요)
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 성공")
    @DeleteMapping("/{memberId}")
    public CustomResponse<String> deleteMember(@PathVariable("memberId") Long memberId) {
        memberCommandService.deleteMember(memberId);
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }


    @Operation(summary = "getOffset")
    @GetMapping("/offset")
    public ResponseEntity<MemberResDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(memberQueryService.getMemberOffset(offset, size));
    }

    @Operation(summary = "getCursor")
    @GetMapping("/cursor")
    public ResponseEntity<MemberResDTO.MemberCursorResDTO> getMemberCursor(
            @RequestParam Long cursor,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(memberQueryService.getMemberCursor(cursor, size));
    }
//
//    @Operation(summary = "비밀번호 수정")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "400", description = "Bad Request",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = @Content(mediaType = "application/json"))
//    })
//    @PostMapping("password-reset")
//    public ResponseEntity<?> resetPassword(@RequestBody MemberReqDTO.ResetPasswordReqDTO resetPasswordReqDTO) {
//        return null;
//    }
//
//    @Operation(summary = "회원 가입")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "400", description = "Bad Request",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "409", description = "Conflict",
//                    content = @Content(mediaType = "application/json"))
//    })
//    @PostMapping("sign-up")
//    public ResponseEntity<?> signUp(@RequestBody MemberReqDTO.SignUpResDTO signUpResDTO) {
//        return null;
//    }
//
//
//    @Operation(summary = "카카오 로그인")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = MemberResDTO.LoginJwtTokenResDTO.class))),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = @Content(mediaType = "application/json"))
//    })
//    @PostMapping("login/kakao")
//    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTO> kakaoLogin(@RequestBody MemberReqDTO.KakaoLoginResDTO KakaoLoginResDTO) {
//        // 프론트가 카카오에게 받은 인가코드를 Req에 넣어서 백엔드에 전달하면
//        // 백엔드가 카카오에서 토큰을 받아오고
//        // 자체적인 서비스 전용 jwt를 만들어서 Res로 보내주는 방식이긴 합니다만, 아직까지도 아리송합니다
//        // 그리고 토큰을 ResBody에 보내면 xss같은 탈취 보안 이슈가 있어 ResHead로 보내야 한다고는 합니다만...
//        // 능력 부족 이슈로 카카오는 뭔가 이해가 어렵네요
//        return null;
//    }
}
