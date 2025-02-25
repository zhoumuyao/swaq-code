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
import java.util.List;

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
                                          @RequestParam String description,
                                          @RequestParam String casualties,
                                          @RequestParam String symptomMessage,
                                          @RequestParam String influenceScope){
        if(StringUtils.isAnyBlank(date, time, latitude, longitude, country, province, urban, description, casualties, symptomMessage, influenceScope)){
            return RestBean.failure(400, -1);
        }
        BiologicalCase biologicalCase = new BiologicalCase();
        biologicalCase.setDate(LocalDate.parse(date));
        biologicalCase.setTime(LocalTime.parse(time));
        biologicalCase.setLongitude(Double.parseDouble(longitude));
        biologicalCase.setLatitude(Double.parseDouble(latitude));
        biologicalCase.setCountry(country);
        biologicalCase.setProvince(province);
        biologicalCase.setUrban(urban);
        biologicalCase.setDescription(description);
        biologicalCase.setCasualties(Integer.parseInt(casualties));
        biologicalCase.setSymptomMessage(symptomMessage);
        biologicalCase.setInfluenceScope(Double.parseDouble(influenceScope));
        int id = service.createCase(biologicalCase);
        if(id == -1){
            return RestBean.failure(400, id);
        }
        return RestBean.success(id);
    }

    @PostMapping("/search_case")
    public RestBean<BiologicalCase> searchCase(@RequestParam int id){
        BiologicalCase biologicalCase = service.selectCase(id);
        if(biologicalCase == null)
            return RestBean.failure(400);
        return RestBean.success(biologicalCase);
    }

    @PostMapping("/view_case")
    public RestBean<List<BiologicalCase>> searchAllCase(){
        //调用service查询案例数据
        List<BiologicalCase> biologicalCaseList = service.searchAllCase();
        return RestBean.success(biologicalCaseList);
    }

    @PostMapping("/delete_case")
    public RestBean<String> deleteCase(@RequestParam int id){
        Boolean isDelete = service.deleteCase(id);
        if(isDelete)
            return RestBean.success("删除成功");
        else
            return RestBean.failure(400, "删除失败");
    }
}
