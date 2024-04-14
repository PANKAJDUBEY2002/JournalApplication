package com.application.journalApplication.service;

import com.application.journalApplication.api.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Exchanger;
@Component
public class WeatherService {


    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather(String city) {
        String finalAPI="http://api.weatherapi.com/v1/current.json?key=ea609faf1b5f4a97982181921241404&q=London&aqi=no";

        ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;

    }
}
