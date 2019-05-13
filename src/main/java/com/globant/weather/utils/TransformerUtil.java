package com.globant.weather.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformerUtil {

    private static final Double KELVIN_TO_CELSIUS = 273.15;
    private static final Double KELVIN_TO_FAHRENHEIT = 1.8;
    private static final Integer KELVIN_TO_FAHRENHEIT_PLUS = 32;
    private static final Integer MILISECONDS = 1000;

    /**
     * Transform kelvin temp to Celsius
     * @param kelvinTemp
     * @param tempFormat
     * @return
     */
    public static String kelvinToCelsius (Double kelvinTemp, String tempFormat) {
        return getTempFormat(kelvinTemp - KELVIN_TO_CELSIUS, tempFormat);
    }

    /**
     * Transform kelvin temp to Fahrenheit
     * @param kelvinTemp
     * @param tempFormat
     * @return
     */
    public static String kelvinToFahrenheit (Double kelvinTemp, String tempFormat) {
        return getTempFormat(KELVIN_TO_FAHRENHEIT * (kelvinTemp - KELVIN_TO_CELSIUS) + KELVIN_TO_FAHRENHEIT_PLUS, tempFormat);
    }

    /**
     * Returns the time in specific format
     * @param time
     * @param timeFormat
     * @return
     */
    public static String timeFormat(Long time, String timeFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat);
        return dateFormat.format(new Date(time * MILISECONDS));
    }

    /**
     * Return a Date with specific format
     * @param time
     * @param dateFormat
     * @return
     */
    public static String dateFromTime(Long time, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(new Date(time * MILISECONDS));
    }

    /**
     * Return a temperature data in specific format
     * @param temp
     * @param tempFormat
     * @return
     */
    private static String getTempFormat(Double temp, String tempFormat) {
        DecimalFormat decimalFormat = new DecimalFormat(tempFormat);
        return decimalFormat.format(temp);
    }
}
