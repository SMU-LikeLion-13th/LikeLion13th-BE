package com.project.likelion13thbe.domain.api.weather;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class AirKoreaService {

    private final String serviceKey;
    private final WebClient webClient;

    public AirKoreaService(@Value("${airkorea.service-key}") String serviceKey) {
        this.serviceKey = serviceKey;
        this.webClient = WebClient.builder()
                .baseUrl("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc")
                .build();
    }

    public AirKoreaResponse getAirQuality(String sidoName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getCtprvnRltmMesureDnsty")
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("returnType", "json")
                        .queryParam("sidoName", sidoName)
                        .queryParam("numOfRows", 1)
                        .queryParam("pageNo", 1)
                        .queryParam("ver", "1.0")
                        .build())
                .retrieve()
                .bodyToMono(AirKoreaResponse.class)
                .block();
    }
}
