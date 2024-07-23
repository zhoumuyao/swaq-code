package com.example.controller;

import com.example.service.WeatherService;
import org.junit.jupiter.api.Test;

public class weatherControllerTest {


    WeatherService weatherService = new WeatherService();
    @Test
    void test() throws Exception {
        String districtId = "320100";
        weatherService.getWeather(districtId);
    }
}
