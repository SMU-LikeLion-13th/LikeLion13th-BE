package com.project.likelion13thbe.global.security.controller;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import com.project.likelion13thbe.global.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<CustomResponse<?>> reissue(HttpServletRequest request, HttpServletResponse response) {

        log.info("[ Auth Controller ] 토큰을 재발급합니다.");

        String refreshToken = getRefreshTokenFromCookie(request);

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(CustomResponse.onFailure(
                            AuthErrorCode.REFRESH_TOKEN_NOT_FOUNT.getCode(),
                            AuthErrorCode.REFRESH_TOKEN_NOT_FOUNT.getMessage()
                    ));
        }
        return ResponseEntity.ok(CustomResponse.onSuccess(authService.reissueToken(refreshToken, response)));
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
