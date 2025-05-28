package com.project.likelion13thbe.global.security.service;

import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.dto.KakaoResponseDTO;
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

    private final String clientId;
    private final String tokenURI;
    private final String userInfoURI;
    private final String redirectURI;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public KakaoService(@Value("${spring.security.oauth2.client.registration.kakao.client-id}") String clientId,
                        @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}") String redirectURI,
                        @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}") String userInfoURI,
                        @Value("${spring.security.oauth2.client.provider.kakao.token-uri}") String tokenURI,
                        MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.clientId = clientId;
        this.redirectURI = redirectURI;
        this.userInfoURI = userInfoURI;
        this.tokenURI = tokenURI;
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String getAccessTokenFromKakao(String code) {
        KakaoResponseDTO.KakaoTokenResponseDTO kakaoTokenResponseDTO = WebClient.create(tokenURI)
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
                // TODO: Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoResponseDTO.KakaoTokenResponseDTO.class)
                .block();

        log.info("[ Kakao Service ] Access Token ---> {}", kakaoTokenResponseDTO.accessToken());
        log.info("[ Kakao Service ] Refresh Token ---> {}", kakaoTokenResponseDTO.refreshToken());
        // 제공 조건: OpenID Connect가 활성된 앱의 토큰 발급 요청인 경우
        // 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info("[ Kakao Service ] Id Token ---> {}", kakaoTokenResponseDTO.idToken());
        log.info("[ Kakao Service ] Scope ---> {}", kakaoTokenResponseDTO.scope());

        return kakaoTokenResponseDTO.accessToken();
    }

    public KakaoResponseDTO.KakaoUserInfoResponseDTO getUserInfo(String accessToken) {

        KakaoResponseDTO.KakaoUserInfoResponseDTO userInfo = WebClient.create(userInfoURI)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                // TODO: Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoResponseDTO.KakaoUserInfoResponseDTO.class)
                .block();

        log.info("[ Kakao Service ] Auth ID ---> {}", userInfo.id());
        log.info("[ Kakao Service ] Nickname ---> {}", userInfo.kakaoAccount().profile().nickName());
        log.info("[ Kakao Service ] ProfileImageUrl ---> {}", userInfo.kakaoAccount().profile().profileImageUrl());

        return userInfo;

    }
}
