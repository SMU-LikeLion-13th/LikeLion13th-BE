package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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


    @Operation(summary = "일반 로그인", description = "CustomLoginFilter에서 로그인함, 컨트롤러 관여x")
    @PostMapping("/login")
    public MemberResDTO.MemberResponseDTO Login() { return null; }


    @Operation(summary = "비밀번호 수정",description = "회원의 정보를 수정합니다.")
    @PatchMapping("{userId}/password-reset/")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    )
    public CustomResponse<String> resetPassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MemberReqDTO.PasswordResetDTO request
    ) {
        memberCommandService.updatePassword(userDetails.getUsername(), request);
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
    public CustomResponse<MemberResDTO.MemberPreviewResDTO> getMember(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        return CustomResponse.onSuccess(memberQueryService.getMember(userDetails.getUsername()));
    }

    @Operation(summary = "로그인한 사용자 정보 조회")
    @GetMapping("/members/me")
    public CustomResponse<MemberResDTO.MemberPreviewResDTO> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return CustomResponse.onSuccess(memberQueryService.getMember(userDetails.getUsername()));
    }


    @Operation(summary = "사용자 정보 페이지네이션 조회, offset 기반")
    @GetMapping("/offset")
    public CustomResponse<MemberResDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ){
        return CustomResponse.onSuccess(memberQueryService.getMemberOffset(offset, size));
    }

    @PreAuthorize("hasRole('ADMIN')")//관리자만 사용자 리스트 조회가능 하게 설정
    @Operation(summary = "사용자 정보 페이지네이션 조회, cursor 기반")
    @GetMapping("/cursor")
    public CustomResponse<MemberResDTO.MemberCursorResDTO> getMemberCursor(@RequestParam(required = false, defaultValue = "0") Long cursor,
                                                                           @RequestParam Integer size) {
        return CustomResponse.onSuccess(memberQueryService.getMemberCursor(cursor, size));
    }

    //회원 탈퇴 (JWT 인증 필요)
    @DeleteMapping("/members/{memberId}")
    @Operation(summary = "회원 탈퇴", description = "회원 계정을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    })
    public CustomResponse<String> deleteMember(@AuthenticationPrincipal UserDetails userDetails){
        memberCommandService.deleteMember(userDetails.getUsername());
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }

}
