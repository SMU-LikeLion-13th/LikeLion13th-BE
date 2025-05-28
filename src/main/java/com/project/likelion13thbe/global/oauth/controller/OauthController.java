//package com.project.likelion13thbe.global.oauth.controller;
//
//import com.project.likelion13thbe.domain.member.entity.Member;
//import com.project.likelion13thbe.domain.member.repository.MemberRepository;
//import com.project.likelion13thbe.global.apiPayload.CustomResponse;
//import com.project.likelion13thbe.global.oauth.dto.KakaoUserInfoResponseDTO;
//import com.project.likelion13thbe.global.oauth.service.KakaoService;
//import com.project.likelion13thbe.global.security.CustomUserDetails;
//import com.project.likelion13thbe.global.security.JwtUtil;
//import com.project.likelion13thbe.global.token.dto.TokenDTO;
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping
//public class OauthController {
//
//    private final KakaoService kakaoService;
//    private final MemberRepository memberRepository;
//    private final JwtUtil jwtUtil;
//
//    @GetMapping("/callback/kakao")
//    public CustomResponse<TokenDTO> callback(@RequestParam("code") String code) {
//
//        // 1. 카카오 인증서버에서 토큰을 발급받는다.
//        // 인가code와 Redirect URL을 파라미터로 전달하여 카카오 인증서버에 요청.
//        String accessToken = kakaoService.getAccessTokenFromKakao(code);
//
//
//        // 2. 1번에서 받은 토큰으로 카카오 리소스 서버에 사용자 정보 요청.
//        KakaoUserInfoResponseDTO userInfo = kakaoService.getUserInfo(accessToken);
//
//        // 3. 회원가입 & 로그인 처리
//        // 여기에 서버 사용자 로그인(인증) 또는 회원가입 로직 추가
//        String email = userInfo.getKakaoAccount().email;
//
//        Member member = memberRepository.findByEmail(email)
//                .orElseGet(() -> {
//                    Member newMember = Member.builder()
//                            .email(email)
//                            .nickname(userInfo.getKakaoAccount().getName())
//                            .password("kakao_oauth")
//                            .build();
//                    return memberRepository.save(newMember);
//                });
//
//        CustomUserDetails userDetails = new CustomUserDetails(email, null, "USER");
//
//        String jwtAccessToken = jwtUtil.createJwtAccessToken(userDetails);
//        String jwtRefreshToken = jwtUtil.createJwtRefreshToken(userDetails);
//
//        TokenDTO tokenDTO = TokenDTO.builder()
//                .accessToken(jwtAccessToken)
//                .refreshToken(jwtRefreshToken)
//                .build();
//
//        return CustomResponse.onSuccess(null);
//    }
//}
//
