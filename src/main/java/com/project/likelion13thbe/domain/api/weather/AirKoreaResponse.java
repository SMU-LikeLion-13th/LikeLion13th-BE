package com.project.likelion13thbe.domain.api.weather;

import lombok.Data;

import java.util.List;

@Data
public class AirKoreaResponse {
    private Response response;

    @Data
    public static class Response {
        private Body body;
    }

    @Data
    public static class Body {
        private List<Item> items;
    }

    @Data
    public static class Item {
        private String stationName;
        private String pm10Value;
        private String pm25Value;
        private String dataTime;
    }
}
