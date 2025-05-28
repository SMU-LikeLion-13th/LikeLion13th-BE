package com.project.likelion13thbe.domain.api.weather;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AirKoreaResponse {
    private Response response;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Body body;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Body {
        private List<Item> items;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Item {
        private String stationName;
        private String pm10Value;
        private String pm25Value;
        private String dataTime;
    }
}
