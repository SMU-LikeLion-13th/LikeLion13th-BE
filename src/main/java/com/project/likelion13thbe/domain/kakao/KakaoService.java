package com.project.likelion13thbe.domain.kakao;


import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class KakaoService {


    private final String clientId;     // API Key
    private final String tokenURI;    // 카카오 인증 서버
    private final String userInfoURI;     // 카카오 리소스 서버
    private final String redirectURI;  // redirect URI
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public KakaoService(@Value("${spring.security.oauth2.client.registration.kakao.client-id}") String clientId,
                        @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}") String redirectURI,
                        @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}") String userInfoURI,
                        @Value("${spring.security.oauth2.client.provider.kakao.token-uri}") String tokenURI, MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.clientId = clientId;
        this.tokenURI = tokenURI;
        this.userInfoURI = userInfoURI;
        this.redirectURI = redirectURI;
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String getAccessTokenFromKakao(String code) {

        KakaoTokenResponseDTO kakaoTokenResponseDto = WebClient.create(tokenURI)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectURI)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDTO.class)
                .block();


        log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());
        log.info(" [Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());

        return kakaoTokenResponseDto.getAccessToken();
    }

    public KakaoUserInfoResponseDTO getUserInfo(String accessToken) {

        KakaoUserInfoResponseDTO userInfo = WebClient.create(userInfoURI)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDTO.class)
                .block();

        log.info("[ Kakao Service ] Auth ID ---> {} ", userInfo.getId());
        log.info("[ Kakao Service ] NickName ---> {} ", userInfo.getKakaoAccount().getProfile().getNickName());
        log.info("[ Kakao Service ] ProfileImageUrl ---> {} ", userInfo.getKakaoAccount().getProfile().getProfileImageUrl());

        return userInfo;
    }
}
