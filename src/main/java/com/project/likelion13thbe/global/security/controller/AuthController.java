package com.project.likelion13thbe.global.security.controller;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.jwt.JwtDTO;
import com.project.likelion13thbe.global.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 토큰 재발급 API
    @Operation(method = "POST", summary = "토큰 재발급")
    @PostMapping("/reissue")
    public CustomResponse<?> reissue(@RequestBody JwtDTO jwtDTO) {
        log.info("[ Auth Controller ] 토큰을 재발급합니다.");

        return CustomResponse.onSuccess(authService.reissueToken(jwtDTO));
    }
}
