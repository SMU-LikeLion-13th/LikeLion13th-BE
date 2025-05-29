package com.project.likelion13thbe.global.apiPayload.exception.handler;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.apiPayload.code.BaseErrorCode;
import com.project.likelion13thbe.global.apiPayload.code.GeneralErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import jakarta.validation.ConstraintViolationException;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //애플리케이션에서 발생하는 커스텀 예외를 처리
    //CustomException 이 감지되면 해당 행동을 하겠다는 의미
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponse<Void>> handleCustomException(CustomException ex) {
        //예외가 발생하면 로그 기록
        log.warn("[ CustomException ]: {}", ex.getCode().getMessage());
        //커스텀 예외에 정의된 에러 코드와 메시지를 포함한 응답 제공
        return ResponseEntity.status(ex.getCode().getHttpStatus())
                .body(ex.getCode().getErrorResponse());
    }
    //ConstraintViolationException 예외 처리
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomResponse<Void>> handleConstraintViolationException(
            ConstraintViolationException ex) {
        log.warn("[ConstraintViolationException]: {}", ex.getMessage());

        BaseErrorCode errorCode = GeneralErrorCode.NOT_FOUND_404;

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(errorCode.getErrorResponse());
    }

    //MethodArgumentNotValidException 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.warn("[MethodArgumentNotValidException]: {}", ex.getMessage());

        BaseErrorCode errorCode = GeneralErrorCode.NOT_FOUND_404;

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(errorCode.getErrorResponse());
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler({Exception.class})
    public ResponseEntity<CustomResponse<String>> handleAllException(Exception ex) {
        log.error("[WARNING] Internal Server Error : {} ", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        ex.printStackTrace();
        CustomResponse<String> errorResponse = CustomResponse.onFailure(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }
}
