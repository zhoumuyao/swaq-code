package com.example.controller;

import com.example.entity.BiologicalCase;
import com.example.entity.RestBean;
import com.example.service.CaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;

@Validated
@RestController
@RequestMapping("/api/case")
public class CaseController {
    @Resource
    CaseService service;

    @PostMapping("/create_case")
    public RestBean<Integer> createCase(@RequestParam String date,
                                          @RequestParam String time,
                                          @RequestParam String longitude,
                                          @RequestParam String latitude,
                                          @RequestParam String country,
                                          @RequestParam String province,
                                          @RequestParam String urban,
                                          @RequestParam String description){
        if(StringUtils.isAnyBlank(date, time, latitude, longitude, country, province, urban, description)){
            return RestBean.failure(400, -1);
        }
        BiologicalCase biologicalCase = new BiologicalCase();
        biologicalCase.setDate(LocalDate.parse(date));
        biologicalCase.setTime(LocalTime.parse(time));
        biologicalCase.setLongitude(Integer.parseInt(longitude));
        biologicalCase.setLatitude(Integer.parseInt(latitude));
        biologicalCase.setCountry(country);
        biologicalCase.setProvince(province);
        biologicalCase.setUrban(urban);
        biologicalCase.setDescription(description);
        int id = service.createCase(biologicalCase);
        if(id == -1){
            return RestBean.failure(400, id);
        }
        return RestBean.success(id);
    }

    @PostMapping("/search_case")
    public RestBean<BiologicalCase> searchCase(@RequestParam int id){
        BiologicalCase biologicalCase = service.selectCase(id);
        return RestBean.success(biologicalCase);
    }
}
