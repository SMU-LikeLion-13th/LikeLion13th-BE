package com.project.likelion13thbe.domain.api.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AirKoreaController {

    private final AirKoreaService airKoreaService;

    @GetMapping("/air/seoul")
    public ResponseEntity<ApiResponse<AirKoreaResponse>> getSeoulAir() {
        AirKoreaResponse result = airKoreaService.getAirQuality("서울");
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
