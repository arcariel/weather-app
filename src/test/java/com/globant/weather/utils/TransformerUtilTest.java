package com.globant.weather.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransformerUtilTest {

    private static final String TEMP_FORMAT = "###.##";
    private static final String TIME_FORMAT = "HH:mm a";
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";
    private static final Double KELVIN_TEMP = 283.16D;
    private static final Long TIME = 1557202928L;

    @Test
    public void kelvinToCelsius() {
        String celsius = TransformerUtil.kelvinToCelsius(KELVIN_TEMP, TEMP_FORMAT);

        assertEquals("10.01", celsius);
    }

    @Test
    public void kelvinToFahrenheit() {
        String fahrenheit = TransformerUtil.kelvinToFahrenheit(KELVIN_TEMP, TEMP_FORMAT);
        assertEquals("50.02", fahrenheit);
    }

    @Test
    public void timeFormat() {
        String time = TransformerUtil.timeFormat(TIME, TIME_FORMAT);
        assertEquals("23:22 PM", time);
    }

    @Test
    public void dateFromTime() {
        String date = TransformerUtil.dateFromTime(TIME, DATE_FORMAT);
        assertEquals("06-05-2019 23:22", date);
    }
}