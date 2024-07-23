package com.example.controller;

import com.example.service.WeatherService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Validated
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Resource
    WeatherService service;


//    @PostMapping("/getWeather")
//    public void baiduWeather() throws Exception {
//        String str = service.getWeather();
//
//    }

    @GetMapping("/{adcode}")
    public String baiduWeather(@PathVariable String adcode) throws Exception {
        return service.getWeather(adcode);
    }

}
