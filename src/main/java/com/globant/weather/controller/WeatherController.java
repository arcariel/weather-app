package com.globant.weather.controller;

import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.WeatherData;
import com.globant.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public WeatherData getCurrentWeather(@PathVariable String city) throws WeatherException {
        return weatherService.getWeatherData(city);
    }

}
