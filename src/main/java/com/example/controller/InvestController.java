package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.example.entity.Invest;
import com.example.entity.InvestVo;
import com.example.entity.RestBean;
import com.example.service.InvestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("api/invest")
public class InvestController {

    @Resource
    InvestService investService;
    @PostMapping("createInvest")
    public RestBean<String> createInfo(@RequestBody InvestVo investVo){
        Invest invest = new Invest();
        System.out.println(investVo);
        invest.setGather(investVo.getGather().toString());
        invest.setPersonelDensity(investVo.getPersonelDensity().toString());
        invest.setDate(LocalDate.parse(investVo.getDate()));
        invest.setTime(LocalTime.parse(investVo.getTime()));
        invest.setId(investVo.getId());
        invest.setHumi(investVo.getHumi());
        invest.setSoil(investVo.getSoil());
        invest.setWater(investVo.getWater());
        invest.setAirQuality(investVo.getAirQuality());
        invest.setTemperature(investVo.getTemperature());
        invest.setPersonelDensity(investVo.getPersonelDensity());
        invest.setWindDirection(investVo.getWindDirection());
        invest.setWeather(investVo.getWeather());
        String res = investService.createInvest(invest);
        return RestBean.success(res);
    }

    @GetMapping("queryInvest")
    public RestBean<Invest> queryInvest(@RequestParam("id") int id){
        Invest invest = investService.queryInvest(id);
        return RestBean.success(invest);
    }


    @PostMapping("/delete_HandlePerson")
    public RestBean<String> deleteHandlePerson(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = investService.deleteHandlePerson(id);
        return RestBean.success(s);
    }

    @PostMapping("/delete_HandleEquipment")
    public RestBean<String> deleteHandleEquipment(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = investService.deleteHandleEquipment(id);
        return RestBean.success(s);
    }

    @PostMapping("/add_HandlePerson")
    public RestBean<String> addHandlePerson( @RequestParam int id,
                                           @RequestParam(value = "persons[]", required = false) int[] persons){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(persons == null){
            return RestBean.failure(400, "请选中参与警务人员");
        }
        String s = investService.addHandlePerson(id, persons);
        return RestBean.success(s);
    }

    @PostMapping("/add_HandleEquipment")
    public RestBean<String> addHandleEquipment(@RequestParam int id,
                                             @RequestParam(value = "equipments[]", required = false) int[] equipments){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(equipments == null){
            return RestBean.failure(400, "请选中使用设备");
        }
        String s = investService.addHandleEquipment(id, equipments);
        return RestBean.success(s);
    }


    @PostMapping("/select_HandlePerson")
    public RestBean<int[]> selectHandlePersons(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] handlePersons = investService.selectHandlePerson(id);
        return RestBean.success(handlePersons);
    }

    @PostMapping("/select_HandleEquipment")
    public RestBean<int[]> selectHandleEquipment(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] handleEquipments = investService.selectHandleEquipment(id);
        return RestBean.success(handleEquipments);
    }
}
