package com.globant.weather.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    @Value("${temperature.format}")
    private String tempFormat;
    @Value("${time.format}")
    private String timeFormat;
    @Value("${date.format}")
    private String dateFormat;
    @Value("${weather.api.base.url}")
    private String uri;
    @Value("${weather.api.app.id}")
    private String appId;
    @Value("${weater.api.lang}")
    private String lang;
    @Value("${weather.general.exception.msg}")
    private String exceptionMsg;

    public String getTempFormat() {
        return tempFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getUri() {
        return uri;
    }

    public String getAppId() {
        return appId;
    }

    public String getLang() {
        return lang;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }
}
