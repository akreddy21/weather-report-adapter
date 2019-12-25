package com.app.weatherreportadapter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "weatherBitTemplate")
    public RestTemplate productWSClient(RestTemplateBuilder builder, AppConfigProperties properties) {
        AppConfigProperties.WeatherBitWSProperties weatherBitWSProperties = properties.getWeatherBitWSProperties();
        return builder.rootUri(weatherBitWSProperties.getRootUri())
                .setReadTimeout(weatherBitWSProperties.getReadTimeout())
                .setConnectTimeout(weatherBitWSProperties.getConnectTimeout())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .requestFactory((Supplier<ClientHttpRequestFactory>) new HttpComponentsClientHttpRequestFactory())
                .errorHandler(new DefaultResponseErrorHandler()).build();
    }
}


