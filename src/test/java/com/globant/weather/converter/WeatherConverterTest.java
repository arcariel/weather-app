package com.globant.weather.converter;

import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.*;
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
public class WeatherConverterTest {

    @InjectMocks
    private WeatherConverter weatherConverter;

    @Mock
    private Properties properties;

    @Value("${temperature.format}")
    private String tempFormat;
    @Value("${time.format}")
    private String timeFormat;
    @Value("${date.format}")
    private String dateFormat;

    @Before
    public void setUp() {
        Mockito.when(properties.getTempFormat()).thenReturn(tempFormat);
        Mockito.when(properties.getTimeFormat()).thenReturn(timeFormat);
        Mockito.when(properties.getDateFormat()).thenReturn(dateFormat);
    }

    @Test
    public void transformWeatherResponse() throws WeatherException {
        WeatherData data = weatherConverter.transformWeatherResponse(get200WeatherDataResponse());

        assertNotNull(data);
        assertEquals("London", data.getCityName());
    }

    @Test(expected = WeatherException.class)
    public void transformWeatherResponseWithNullValues() throws WeatherException {
        weatherConverter.transformWeatherResponse(getWeatherDataResponseWithNullValues());
    }

    @Test(expected = WeatherException.class)
    public void transformWeatherResponseWithEmpties() throws WeatherException {
        weatherConverter.transformWeatherResponse(get200WeatherDataResponseWithEmpties());
    }

    private WeatherDataResponse get200WeatherDataResponse() {
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

        return data;
    }

    private WeatherDataResponse getWeatherDataResponseWithNullValues() {
        WeatherDataResponse data = new WeatherDataResponse();

        data.setName("London");

        Weather weather = new Weather();
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        data.setWeather(weatherList);

        WeatherMain main = new WeatherMain();
        data.setMain(main);

        WeaterSys weaterSys = new WeaterSys();
        weaterSys.setSunrise(1557202928L);
        data.setSys(weaterSys);

        return data;
    }

    private WeatherDataResponse get200WeatherDataResponseWithEmpties() {
        WeatherDataResponse data = new WeatherDataResponse();

        data.setDt(1557265484L);
        data.setName("");

        Weather weather = new Weather();
        weather.setDescription("");
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

        return data;
    }
}