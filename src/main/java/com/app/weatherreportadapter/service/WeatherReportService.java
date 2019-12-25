package com.app.weatherreportadapter.service;

import com.app.weatherreportadapter.constant.Criteria;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WeatherReportService {
    String getCurrentWeatherReport(Criteria criteria, Map<String, String> params);
}
