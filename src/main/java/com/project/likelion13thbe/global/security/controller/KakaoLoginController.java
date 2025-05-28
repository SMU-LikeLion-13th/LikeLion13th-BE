package com.project.likelion13thbe.global.security.controller;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.dto.KakaoResponseDTO;
import com.project.likelion13thbe.global.security.entity.CustomUserDetails;
import com.project.likelion13thbe.global.security.jwt.JwtDTO;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import com.project.likelion13thbe.global.security.service.KakaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final MemberRepository memberRepository;
    private final MemberCommandService memberCommandService;
    private final JwtUtil jwtUtil;

    @GetMapping("/callback/kakao")
    public CustomResponse<?> callback(@RequestParam("code") String code,
                                      HttpServletResponse response) {

        // 1. ㅋㅏ카오 인증 서버에서 토큰을 발급받는다
        // 인가 코드와 Redirect URL을 파라미터로 전달하여 카카오 인증 서버에 요청
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        // 2. 1번에서 받은 토큰으로 카카오 리소스 서버에 사용자 정보 요청
        KakaoResponseDTO.KakaoUserInfoResponseDTO userInfo = kakaoService.getUserInfo(accessToken);

        // 3. 회원가입 & 로그인
        // 서버 사용자 로그인(인증) 또는 회원가입 로직 추가
        Optional<Member> optionalMember = memberRepository.findByEmailAndNotDeleted(userInfo.kakaoAccount().email());

        Member member;

        if (optionalMember.isEmpty()) {
            // Sign up
            MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO = MemberConverter.kakaoToMemberRequestDTO(userInfo);
            memberCommandService.createMember(memberCreateRequestDTO);
            log.info("[ KakaoLoginController ] 카카오 유저 회원 가입 완료");

            // 쿼리 두 번 :(
            member = memberRepository.findByEmailAndNotDeleted(userInfo.kakaoAccount().email())
                    .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        } else {
            log.info("[ KakaoLoginController ] 이미 존재하는 회원입니다. 로그인으로 넘어갑니다.");
            member = optionalMember.get();
        }
        // Sign in
        // 멤버를 받아서 jwt 반환
        // 위의 member를 CustomUserDetails로 변환 -> jwt 생성 -> 반환 하면 끝 !
        CustomUserDetails userDetails = new CustomUserDetails(member);

        log.info("[ KakaoLoginController ] 로그인에 성공했습니다.");
        String access = jwtUtil.createJwtAccessToken(userDetails);
        String refresh = jwtUtil.createJwtRefreshToken(userDetails);

        Cookie refreshCookie = createCookie("refreshToken", refresh);
        response.addCookie(refreshCookie);

        JwtDTO jwtDTO = JwtDTO.builder().accessToken(access).build();
        
        return CustomResponse.onSuccess(jwtDTO);

    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
