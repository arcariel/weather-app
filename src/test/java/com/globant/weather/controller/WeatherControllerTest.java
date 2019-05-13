package com.globant.weather.controller;

import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.WeatherData;
import com.globant.weather.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController controller;

    @Before
    public void setUp() throws Exception {
        Mockito.when(weatherService.getWeatherData("London"))
                .thenReturn(getWeatherData());
    }

    @Test
    public void getCurrentWeather() throws WeatherException {
        WeatherData response = controller.getCurrentWeather("London");
        assertNotNull(response);
        assertEquals(response.getCityName(), getWeatherData().getCityName());
    }

    @Test
    public void invalidPath() throws Exception {
        mockMvc.perform(get("/weather/city/London"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void notFound() throws Exception{
        mockMvc.perform(get("/weather/city/badValue"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void invalidMethod() throws Exception {

        class WeatherRequest {
            String city;
            public WeatherRequest(String city) {
                this.city = city;
            }
            public String getCity() {
                return city;
            }
            public void setCity(String city) {
                this.city = city;
            }
        }
        WeatherRequest request = new WeatherRequest("London");
        mockMvc.perform(post("weather", request))
                .andExpect(status().isNotFound());
    }

    private WeatherData getWeatherData() {
        return new WeatherData("08-May-2019 15:45", "London",
                "Rain", "10.12", "50.22",
                "9:20 AM", "20:20 PM");
    }
}