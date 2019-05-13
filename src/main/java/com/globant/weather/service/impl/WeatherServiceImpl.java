package com.globant.weather.service.impl;

import com.globant.weather.client.WeatherClient;
import com.globant.weather.converter.WeatherConverter;
import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.WeatherData;
import com.globant.weather.model.WeatherDataResponse;
import com.globant.weather.service.WeatherService;
import com.globant.weather.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private Properties properties;
    @Autowired
    private WeatherClient client;
    @Autowired
    private WeatherConverter converter;

    @Override
    public WeatherData getWeatherData(String cityName) throws WeatherException {
        ResponseEntity<WeatherDataResponse> response = client.getWeatherbyCity(getUrl(cityName));
        if (response.getStatusCode() == HttpStatus.OK) {
            return converter.transformWeatherResponse(response.getBody());
        } else {
            throw new WeatherException(properties.getExceptionMsg());
        }
    }

    private String getUrl(String cityName) {
        return properties.getUri() + cityName + properties.getAppId() + properties.getLang();
    }



}
