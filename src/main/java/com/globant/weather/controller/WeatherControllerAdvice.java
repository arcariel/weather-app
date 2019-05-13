package com.globant.weather.controller;

import com.globant.weather.exception.WeatherErrorResponse;
import com.globant.weather.exception.WeatherServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class WeatherControllerAdvice {

    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<WeatherErrorResponse> serviceException(final WeatherServiceException serviceException) {
        return new ResponseEntity<>(serviceException.getErrorResponse(), serviceException.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<WeatherServiceException> argumentTypeMismatchException(
            final MethodArgumentTypeMismatchException mismatchException) {

        return new ResponseEntity<>(new WeatherServiceException(mismatchException.getLocalizedMessage(),
                mismatchException.getCause(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    public ResponseEntity<WeatherServiceException> methodNotAllowed(final HttpClientErrorException.MethodNotAllowed notAllowed) {
        return new ResponseEntity<>(new WeatherServiceException(notAllowed.getLocalizedMessage(), notAllowed.getCause(),
                HttpStatus.METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
