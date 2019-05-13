package com.globant.weather.service;

import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.WeatherData;

public interface WeatherService {

    /**
     * Returns the weather info of specific city
     * @param cityName
     * @return WeatherData
     * @throws WeatherException
     */
    WeatherData getWeatherData(String cityName) throws WeatherException;
}
