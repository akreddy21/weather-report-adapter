package com.app.weatherreportadapter.config;

import com.app.weatherreportadapter.constant.Criteria;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "properties")
public class AppConfigProperties {

    private WeatherBitWSProperties weatherBitWSProperties;

    @Data
    public static class WSProperties {
        private String rootUri;
        private Duration connectTimeout;
        private Duration readTimeout;
    }

    @Data
    public static class WeatherBitWSProperties extends WSProperties {
        private String apiKey;
        private Map<Criteria, String> criteriaUri;
    }
}