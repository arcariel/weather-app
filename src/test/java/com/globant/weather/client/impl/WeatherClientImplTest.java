package com.globant.weather.client.impl;

import com.globant.weather.client.WeatherClient;
import com.globant.weather.model.WeaterSys;
import com.globant.weather.model.Weather;
import com.globant.weather.model.WeatherDataResponse;
import com.globant.weather.model.WeatherMain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class WeatherClientImplTest {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=ed5265cc726bc8654e4131ba2cdc2be7&lang=es";

    @InjectMocks
    private WeatherClient weatherClient = new WeatherClientImpl();

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp () {
        Mockito.when(restTemplate.getForEntity(URL, WeatherDataResponse.class))
                .thenReturn(get200WeatherDataResponse());
    }

    @Test
    public void getWeatherbyCity() {
        ResponseEntity<WeatherDataResponse> response =
                weatherClient.getWeatherbyCity(URL);

        assertNotNull(response);
        assertEquals("London", response.getBody().getName());

    }

    private ResponseEntity<WeatherDataResponse> get200WeatherDataResponse() {
        WeatherDataResponse data = new WeatherDataResponse();

        data.setDt(1557265484L);
        data.setName("London");

        Weather weather = new Weather();
        weather.setDescription("rain");
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        data.setWeather(weatherList);

        WeatherMain main = new WeatherMain();
        main.setTemp(283.16);
        data.setMain(main);

        WeaterSys weaterSys = new WeaterSys();
        weaterSys.setSunrise(1557202928L);
        weaterSys.setSunset(1557257521L);
        data.setSys(weaterSys);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}