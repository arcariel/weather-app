package com.globant.weather.model;

public class WeatherData {

    private String currentDate;
    private String cityName;
    private String description;
    private String fahrenheitTemp;
    private String celciusTemp;
    private String sunrise;
    private String sunset;

    public WeatherData(String currentDate, String cityName, String description, String fahrenheitTemp,
                       String celciusTemp, String sunrise, String sunset) {
        this.currentDate = currentDate;
        this.cityName = cityName;
        this.description = description;
        this.fahrenheitTemp = fahrenheitTemp;
        this.celciusTemp = celciusTemp;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFahrenheitTemp() {
        return fahrenheitTemp;
    }

    public void setFahrenheitTemp(String fahrenheitTemp) {
        this.fahrenheitTemp = fahrenheitTemp;
    }

    public String getCelciusTemp() {
        return celciusTemp;
    }

    public void setCelciusTemp(String celciusTemp) {
        this.celciusTemp = celciusTemp;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
