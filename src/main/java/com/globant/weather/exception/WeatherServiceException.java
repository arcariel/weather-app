package com.globant.weather.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class WeatherServiceException extends RuntimeException{

    private HttpStatus httpStatus;
    private WeatherErrorResponse errorResponse;

    public WeatherServiceException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorResponse = new WeatherErrorResponse(new Date(), message, this.httpStatus.value());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public WeatherErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(WeatherErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
