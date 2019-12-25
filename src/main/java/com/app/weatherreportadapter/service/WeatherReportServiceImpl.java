package com.app.weatherreportadapter.service;

import com.app.weatherreportadapter.constant.Criteria;
import com.app.weatherreportadapter.ws.client.weatherbit.WeatherBitWSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeatherReportServiceImpl implements WeatherReportService {

    private WeatherBitWSClient weatherBitWSClient;
    private static final Logger LOG = LoggerFactory.getLogger(WeatherReportServiceImpl.class);

    @Autowired
    public WeatherReportServiceImpl(WeatherBitWSClient weatherBitWSClient) {
        this.weatherBitWSClient = weatherBitWSClient;
    }

    @Override
    public String getCurrentWeatherReport(Criteria criteria, Map<String, String> params) {
        return weatherBitWSClient.getCurrentWeatherReport(criteria, params);
    }
}
