package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.example.entity.Invest;
import com.example.entity.RestBean;
import com.example.service.InvestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/invest")
public class InvestController {

    @Resource
    InvestService investService;
    @PostMapping("createInvest")
    public RestBean<String> createInfo(@RequestBody Invest invest){
        System.out.println(invest);
        invest.setGather(invest.getGather().toString());
        invest.setPersonelDensity(invest.getPersonelDensity().toString());
        String res = investService.createInvest(invest);
        return RestBean.success(res);
    }

    @GetMapping("queryInvest")
    public RestBean<Invest> queryInvest(@RequestParam("id") int id){
        Invest invest = investService.queryInvest(id);
        return RestBean.success(invest);
    }
}
