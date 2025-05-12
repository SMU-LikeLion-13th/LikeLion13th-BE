package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Member",description = "멤버 API")
@RequestMapping("/api/v1")
@RestController
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    public MemberController(MemberCommandService memberCommandService, MemberQueryService memberQueryService) {
        this.memberCommandService = memberCommandService;
        this.memberQueryService = memberQueryService;
    }

    @Operation(summary = "카카오 로그인") // 카카오는 get인걸로 알고는 있는데 잘 모르겠다~
    @PostMapping("/oauth/kakao")
    public MemberResDTO.MemberResponseDTO postKakaoLogin() { return null; }


    @Operation(summary = "일반 로그인")
    @PostMapping("/login")
    public MemberResDTO.MemberResponseDTO postLogin() { return null; }


    @Operation(summary = "비밀번호 수정",description = "회원의 정보를 수정합니다.")
    @PatchMapping("{userId}/password-reset/")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    )
    public CustomResponse<String> resetPassword(
            @PathVariable Long userId,
            @RequestBody MemberReqDTO.PasswordResetDTO request
    ) {
        memberCommandService.updatePassword(userId, request);
        return CustomResponse.onSuccess("비밀번호 변경 성공");
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<CustomResponse<MemberResDTO.MemberCreateResDTO>> CreateMember(
            @RequestBody MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO
            ){
        CustomResponse<MemberResDTO.MemberCreateResDTO> response =
                CustomResponse.onSuccess(memberCommandService.createMember(memberCreateReqDTO));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "회원 조회")
    @GetMapping("/members/{memberId}")
    public CustomResponse<MemberResDTO.MemberPreviewResDTO> getMember(@PathVariable Long memberId){
        return CustomResponse.onSuccess(memberQueryService.getMember(memberId));
    }

    @Operation(summary = "사용자 정보 페이지네이션 조회, offset 기반")
    @GetMapping("/offset")
    public CustomResponse<MemberResDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ){
        return CustomResponse.onSuccess(memberQueryService.getMemberOffset(offset, size));
    }

    //회원 탈퇴 (JWT 인증 필요)
    @DeleteMapping("/memvers/{memberID-d}")
    @Operation(summary = "회원 탈퇴", description = "회원 계정을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    })
    public CustomResponse<String> deleteMember(@PathVariable Long memberID){
        memberCommandService.deleteMember(memberID);
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }

}
