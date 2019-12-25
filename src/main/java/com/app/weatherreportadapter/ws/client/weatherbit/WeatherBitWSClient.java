package com.app.weatherreportadapter.ws.client.weatherbit;

import com.app.weatherreportadapter.config.AppConfigProperties;
import com.app.weatherreportadapter.constant.Criteria;
import com.app.weatherreportadapter.ws.client.WSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
public class WeatherBitWSClient {
    private RestTemplate restTemplate;
    private AppConfigProperties appConfigProperties;
    private AppConfigProperties.WeatherBitWSProperties weatherBitWSProperties;

    private static final String CURRENT_REPORT_BY_POSTAL_CODE_URI =
            "/v2.0/current?postal_code={postalCode}&country={country}&key={key}";

    private static final String CURRENT_REPORT_BY_LAT_LON_URI =
            "/v2.0/current?lat={lat}&lon={lon}&key={key}";

    private static final Logger LOG = LoggerFactory.getLogger(WeatherBitWSClient.class);

    @Autowired
    public WeatherBitWSClient(@Qualifier("weatherBitTemplate") RestTemplate restTemplate,
                              AppConfigProperties appConfigProperties) {
        this.restTemplate = restTemplate;
        this.appConfigProperties = appConfigProperties;
        this.weatherBitWSProperties = this.appConfigProperties.getWeatherBitWSProperties();
    }

    public String getCurrentWeatherReport(Criteria criteria, Map<String, String> params) {
        return getWeatherBitResponse(weatherBitWSProperties.getCriteriaUri().get(criteria), params);
    }

    private String getWeatherBitResponse(String path, Map<String, String> params) {
        try {
            params.put("key", weatherBitWSProperties.getApiKey());
            ResponseEntity<String> responseEntity = restTemplate.exchange(path, HttpMethod.GET,
                    new HttpEntity<>(null),
                    String.class, params);
            return WSClientUtil.handleResponse(responseEntity);
        } catch (Exception ex) {
            WSClientUtil.wrapException(ex);
            LOG.error(ex.getMessage(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}
