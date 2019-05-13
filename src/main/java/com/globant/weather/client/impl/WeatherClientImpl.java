package com.globant.weather.client.impl;

import com.globant.weather.client.WeatherClient;
import com.globant.weather.model.WeatherDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherClientImpl implements WeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<WeatherDataResponse> getWeatherbyCity(String url) {
        return restTemplate.getForEntity(url, WeatherDataResponse.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
