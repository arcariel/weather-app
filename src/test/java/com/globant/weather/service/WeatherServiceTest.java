package com.globant.weather.service;

import com.globant.weather.client.WeatherClient;
import com.globant.weather.converter.WeatherConverter;
import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.*;
import com.globant.weather.service.impl.WeatherServiceImpl;
import com.globant.weather.utils.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

@SpringBootTest
@SpringBootConfiguration
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class WeatherServiceTest {

    private static final String URL_SUCCESS = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=ed5265cc726bc8654e4131ba2cdc2be7&lang=es";
    private static final String URL_404_EXCEPTION = "http://api.openweathermap.org/data/2.5/weather?q=Mexico&APPID=ed5265cc726bc8654e4131ba2cdc2be7&lang=es";
    private static final String URL_400_EXCEPTION = "http://api.openweathermap.org/data/2.5/weather?q=400&APPID=ed5265cc726bc8654e4131ba2cdc2be7&lang=es";
    private static final String URL_500_EXCEPTION = "http://api.openweathermap.org/data/2.5/weather?q=500&APPID=ed5265cc726bc8654e4131ba2cdc2be7&lang=es";

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Mock
    private Properties properties;
    @Mock
    private WeatherClient client;
    @Mock
    private WeatherConverter converter;

    @Value("${weather.api.base.url}")
    private String uri;
    @Value("${weather.api.app.id}")
    private String appId;
    @Value("${weater.api.lang}")
    private String lang;
    @Value("${temperature.format}")
    private String tempFormat;
    @Value("${time.format}")
    private String timeFormat;
    @Value("${date.format}")
    private String dateFormat;

    @Before
    public void setUp() throws WeatherException {
        Mockito.when(properties.getUri()).thenReturn(uri);
        Mockito.when(properties.getAppId()).thenReturn(appId);
        Mockito.when(properties.getLang()).thenReturn(lang);
        Mockito.when(properties.getTempFormat()).thenReturn(tempFormat);
        Mockito.when(properties.getTimeFormat()).thenReturn(timeFormat);
        Mockito.when(properties.getDateFormat()).thenReturn(dateFormat);

        Mockito.when(client.getWeatherbyCity(URL_SUCCESS))
                .thenReturn(get200WeatherDataResponse());

        Mockito.when(client.getWeatherbyCity(URL_404_EXCEPTION))
                .thenReturn(get404ExceptionResponse());
        Mockito.when(client.getWeatherbyCity(URL_400_EXCEPTION))
                .thenReturn(get400ExceptionResponse());
        Mockito.when(client.getWeatherbyCity(URL_500_EXCEPTION))
                .thenReturn(get500ExceptionResponse());

        Mockito.when(converter.transformWeatherResponse(Mockito.any()))
                .thenReturn(getWeather());
    }

    @Test
    public void getWeatherDataSuccess() throws WeatherException {
        WeatherData data = this.weatherService.getWeatherData("London");

        assertNotNull(data);
        assertEquals("London", data.getCityName());
    }

    @Test (expected = WeatherException.class)
    public void get404Exception() throws WeatherException {
        this.weatherService.getWeatherData("Mexico");
    }

    @Test (expected = WeatherException.class)
    public void get400Exception() throws WeatherException {
        this.weatherService.getWeatherData("400");
    }

    @Test (expected = WeatherException.class)
    public void get500Exception() throws WeatherException {
        this.weatherService.getWeatherData("500");
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

    private ResponseEntity<WeatherDataResponse> get404ExceptionResponse() {
        return new ResponseEntity<>(new WeatherDataResponse(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<WeatherDataResponse> get400ExceptionResponse() {
        return new ResponseEntity<>(new WeatherDataResponse(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<WeatherDataResponse> get500ExceptionResponse() {
        return new ResponseEntity<>(new WeatherDataResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private WeatherData getWeather() {
        return new WeatherData("08-May-2019 15:45", "London",
                "Rain", "10.12", "50.22",
                "9:20 AM", "20:20 PM");
    }
}