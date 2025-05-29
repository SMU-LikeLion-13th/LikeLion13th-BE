package com.project.likelion13thbe.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.likelion13thbe.domain.member.dto.request.LoginReqDTO;
import com.project.likelion13thbe.global.RedisDao;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.auth.CustomUserDetails;
import com.project.likelion13thbe.global.security.jwt.JwtDTO;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import com.project.likelion13thbe.global.security.auth.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.project.likelion13thbe.global.apiPayload.code.AuthErrorCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;

@Slf4j
//redisDao를 멤버로 선언하면 생성자 인자 문제가 발생하여, 생성자를 직접 만들었습니다.
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    //인증 처리 주체
    private final AuthenticationManager authenticationManager;
    //jwt 유틸 발급 클래스
    private final JwtUtil jwtUtil;
    //redis 작업 관련 클래스
    private final RedisDao redisDao;

    //로그인 시도 메서드
    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response) throws AuthenticationException {

        //로그인 시도
        log.info("[ Login Filter ]  로그인 시도 : Custom Login Filter 작동 ");
        ObjectMapper objectMapper = new ObjectMapper();
        LoginReqDTO requestBody;
        try {
            //요청 바디 -> dto로 파싱
            requestBody = objectMapper.readValue(request.getInputStream(), LoginReqDTO.class);
        } catch (IOException e) {
            //파싱에 실패했을 경우
            throw new AuthException(AuthErrorCode.NOT_FOUND_404);
        }

        //Request Body 에서 추출 (사용자 입력값 추출)
        String email = requestBody.email(); //Email 추출
        String password = requestBody.password(); //password 추출
        log.info("[ Login Filter ]  Email ---> {} ", email);
        log.info("[ Login Filter ]  Password ---> {} ", password);

        //UserNamePasswordToken 생성 (인증용 토큰 객체)
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(email, password, null);


        log.info("[ Login Filter ] 인증용 객체 UsernamePasswordAuthenticationToken 이 생성되었습니다. ");
        log.info("[ Login Filter ] 인증을 시도합니다.");

        //AuthenticationManager를 통해 실제 인증 수행
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시
    @Override
    protected void successfulAuthentication(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain,
            @NonNull Authentication authentication) throws IOException {


        log.info("[ Login Filter ] 로그인에 성공 하였습니다.");

        //인증된 사용자 정보 꺼내기
        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();


        //Client 에게 줄 Response 를 Build
        //DTO가 record형이었으므로, 생성자 방식으로 변경
        JwtDTO jwtDto = new JwtDTO(
                jwtUtil.createJwtAccessToken(customUserDetails),
                jwtUtil.createJwtRefreshToken(customUserDetails)
        );


        //redis에 유저 이메일, refreshToken, TTL저장


        // CustomResponse 사용하여 응답 통일
        CustomResponse<JwtDTO> responseBody = CustomResponse.onSuccess(jwtDto);

        //JSON 변환
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value()); //Response 의 Status 를 200으로 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        //Body 에 토큰이 담긴 Response 쓰기
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
    
    //로그인에 실패했을 경우
    @Override
    protected void unsuccessfulAuthentication(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull AuthenticationException failed) throws IOException {

        log.info("[ Login Filter ] 로그인에 실패하였습니다.");

        String errorCode;
        String errorMessage;
        
        //예외 종류에 따른 응답 설정
        if (failed instanceof BadCredentialsException) {
            errorCode = String.valueOf(HttpStatus.UNAUTHORIZED.value());
            errorMessage = "잘못된 정보입니다.";
        } else if (failed instanceof LockedException) {
            errorCode = String.valueOf(HttpStatus.LOCKED.value());
            errorMessage = "계정이 잠금 상태입니다.";
        } else if (failed instanceof DisabledException) {
            errorCode = String.valueOf(HttpStatus.FORBIDDEN.value());
            errorMessage = "계정이 비활성화 되었습니다.";
        } else if (failed instanceof UsernameNotFoundException) {
            errorCode = String.valueOf(HttpStatus.NOT_FOUND.value());
            errorMessage = "계정을 찾을 수 없습니다.";
        } else if (failed instanceof AuthenticationServiceException) {
            errorCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
            errorMessage = "Request Body 파싱 중 오류가 발생했습니다.";
        } else {
            errorCode = String.valueOf(HttpStatus.UNAUTHORIZED.value());
            errorMessage = "인증에 실패했습니다.";
        }
        
        //실패했을 때의 응답 생성
        // CustomResponse 사용하여 응답 통일
        CustomResponse<JwtDTO> responseBody = CustomResponse.onFailure(errorCode, errorMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(Integer.parseInt(errorCode)); // HTTP 상태 코드 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody)); // 응답 변환 및 출력
    }
}
