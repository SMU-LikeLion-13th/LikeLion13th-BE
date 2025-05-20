package com.project.likelion13thbe.global.security;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.token.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
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
@Tag(name = "토큰 발급 API", description = "토큰 발급 API입니다.")
public class AuthController {

    private final AuthService authService;

    //토큰 재발급 API
    @Operation(method = "POST", summary = "토큰 재발급", description = "토큰 재발급. accessToken과 refreshToken을 body에 담아서 전송합니다.")
    @PostMapping("/reissue")
    public CustomResponse<?> reissue(@RequestBody TokenDTO jwtDto) throws AuthException {

        log.info("[ Auth Controller ] 토큰을 재발급합니다. ");

        return CustomResponse.onSuccess(authService.reissueToken(jwtDto));
    }
}
