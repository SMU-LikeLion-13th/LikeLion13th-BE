package com.project.likelion13thbe.domain.openapi.controller;

import com.project.likelion13thbe.domain.openapi.service.OpenApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/openapi")
public class OpenApiController {

    @Autowired
    private OpenApiService openApiService;

    @PostMapping("/ask")
    public String askQuestion(@RequestBody String question) {
        return openApiService.getOpenApiResponse(question);
    }
}
