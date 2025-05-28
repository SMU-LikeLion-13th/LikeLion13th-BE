package com.project.likelion13thbe.domain.openapi.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenApiServiceImpl implements OpenApiService {

    private static final String API_KEY = "{$API_KEY}";
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions"; // 실제 API 엔드포인트 확인 필요

    public String getOpenApiResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // JWT ?
        headers.set("Authorization", "Bearer " + API_KEY);

        String requestBody = String.format("{\"model\": \"deepseek-chat\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", prompt);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // API 호출
        ResponseEntity<String> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody();
    }
}