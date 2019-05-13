package com.globant.weather.converter;

import com.globant.weather.exception.WeatherException;
import com.globant.weather.model.WeatherData;
import com.globant.weather.model.WeatherDataResponse;
import com.globant.weather.utils.Properties;
import com.globant.weather.utils.TransformerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Component
public class WeatherConverter {

    @Autowired
    private Properties properties;

    /**
     * Transform the Service response to a weather model
     * @param response
     * @return
     */
    public WeatherData transformWeatherResponse(WeatherDataResponse response) throws WeatherException{
        WeatherData data;
        try {
            data = new WeatherData(
                TransformerUtil.dateFromTime(response.getDt(), properties.getDateFormat()),
                response.getName(),
                response.getWeather().stream()
                        .map(weater -> weater.getDescription())
                        .collect(Collectors.joining(", ")),
                TransformerUtil.kelvinToFahrenheit(response.getMain().getTemp(), properties.getTempFormat()),
                TransformerUtil.kelvinToCelsius(response.getMain().getTemp(), properties.getTempFormat()),
                TransformerUtil.timeFormat(response.getSys().getSunrise(), properties.getTimeFormat()),
                TransformerUtil.timeFormat(response.getSys().getSunset(), properties.getTimeFormat()));
        } catch (NullPointerException npe) {
            throw new WeatherException(npe.getMessage());
        } catch (Exception e) {
            throw new WeatherException(e.getMessage());
        }
        if (StringUtils.isEmpty(data.getCityName()) || StringUtils.isEmpty(data.getDescription()))
            throw new WeatherException(properties.getExceptionMsg());
        return  data;
    }
}
