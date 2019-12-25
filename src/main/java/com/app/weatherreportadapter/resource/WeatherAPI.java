package com.app.weatherreportadapter.resource;


import com.app.weatherreportadapter.constant.Criteria;
import com.app.weatherreportadapter.service.WeatherReportService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherAPI {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherAPI.class);
    private WeatherReportService weatherReportService;

    @Autowired
    public WeatherAPI(WeatherReportService weatherReportService) {
        this.weatherReportService = weatherReportService;
    }

    @GetMapping(value = "/current/{criteria}")
    public String getCurrentWeatherReport(@PathVariable Criteria criteria, @RequestParam Map<String, String> params) {
        if (Objects.isNull(criteria) || MapUtils.isEmpty(params)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return weatherReportService.getCurrentWeatherReport(criteria, params);
    }
}
