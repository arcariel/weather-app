package com.globant.weather.client;

import com.globant.weather.model.WeatherDataResponse;
import org.springframework.http.ResponseEntity;

public interface WeatherClient {

    /**
     * Return the weather data from the datasource
     * @param url
     * @return
     */
    ResponseEntity<WeatherDataResponse> getWeatherbyCity(String url);
}
