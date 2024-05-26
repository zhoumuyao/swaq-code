package com.example.controller;


import com.example.entity.*;
import com.example.service.RiskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/risk")
public class RiskController {

    @Resource
    RiskService service;

    @PostMapping("/create_plan")
    public RestBean<String> createPlan (@RequestParam int id,
                                        @RequestParam String date,
                                        @RequestParam String time,
                                        @RequestParam String longitude,
                                        @RequestParam String latitude,
                                        @RequestParam String country,
                                        @RequestParam String province,
                                        @RequestParam String urban,
                                        @RequestParam String description,
                                        @RequestParam int type,
                                        @RequestParam String method,
                                        @RequestParam boolean isUpdate){
        if(StringUtils.isAnyBlank(date, time, latitude, longitude, country, province, urban, description) || type < 0 || id < 0 ) {
            return RestBean.failure(400, "请完成填写所有参数");
        }
        RiskPlan riskPlan = new RiskPlan();
        riskPlan.setId(id);
        riskPlan.setDate(LocalDate.parse(date));
        riskPlan.setTime(LocalTime.parse(time));
        riskPlan.setLongitude(Integer.parseInt(longitude));
        riskPlan.setLatitude(Integer.parseInt(latitude));
        riskPlan.setCountry(country);
        riskPlan.setProvince(province);
        riskPlan.setUrban(urban);
        riskPlan.setDescription(description);
        riskPlan.setType(type);
        riskPlan.setMethod(Integer.parseInt(method));
        String s;
        if(isUpdate){
            s = service.updatePlan(riskPlan);
        } else{
            s = service.createPlan(riskPlan);
        }
        if(s == null){
            return RestBean.failure(400, "参数错误");
        }
        else{
            return RestBean.success(s);
        }
    }

    @PostMapping("/update_riskIdentification")
    public RestBean<String> updateRiskIdentification (@RequestParam int id,
                                                      @RequestParam int objectClass,
                                                      @RequestParam int sampleType,
                                                      @RequestParam String sampleContent,
                                                      @RequestParam int testMethod,
                                                      @RequestParam String sampleRequirement
    ){
        if(StringUtils.isAnyBlank(sampleRequirement, sampleContent) || testMethod < 0 || sampleType < 0 || objectClass < 0) {
            return RestBean.failure(400, "请完成填写所有参数");
        }
        RiskIdentification riskIdentification = new RiskIdentification();
        riskIdentification.setId(id);
        riskIdentification.setSampleType(sampleType);
        riskIdentification.setSampleContent(sampleContent);
        riskIdentification.setTestMethod(testMethod);
        riskIdentification.setSampleRequirement(sampleRequirement);
        riskIdentification.setObjectClass(objectClass);
        String s = service.updateRiskIdentification(riskIdentification);
        if(s == null){
            return RestBean.failure(400, "参数错误");
        }
        else{
            return RestBean.success(s);
        }
    }

    @PostMapping("/add_person")
    public RestBean<Integer> addPerson(@RequestParam int id,
                                      @RequestParam int pid,
                                      @RequestParam String pname){
        if(id < 0 || pid < 0 || pname == null){
            return RestBean.failure(400, -1);
        }
        Person person = new Person();
        person.setId(pid);
        person.setName(pname);
        return RestBean.success(2);
    }

    @PostMapping("/select_riskPlan")
    public RestBean<RiskPlan> searchRiskPlan(@RequestParam int id){
        RiskPlan riskPlan = service.selectRiskPlan(id);
        if(riskPlan == null){
            return RestBean.failure(400, null);
        }
        return RestBean.success(riskPlan);
    }

    @PostMapping("/select_person")
    public RestBean<List<Person>> searchPersonList(@RequestParam(required = false, defaultValue = "0") int id,
                                                @RequestParam(required = false, defaultValue = "") String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        List<Person> personList = service.searchPersonList(person);
        return RestBean.success(personList);
    }

    @PostMapping("/select_equipment")
    public RestBean<List<Equipment>> searchEquipmentList(@RequestParam(required = false, defaultValue = "0") int id,
                                                 @RequestParam(required = false, defaultValue = "") String name){
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName(name);
        List<Equipment> equipmentList = service.searchEquipmentList(equipment);
        return RestBean.success(equipmentList);
    }



}
