package com.example.controller;

import com.example.entity.DangerInfo;
import com.example.entity.Invest;
import com.example.entity.RestBean;
import com.example.service.DangerInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/dangerInfo")
public class dangerInfoController {
    @Resource
    DangerInfoService dangerInfoService;

    @GetMapping("queryDanger")
    public RestBean<DangerInfo> queryDanger(@RequestParam("id") int id){
        DangerInfo res = dangerInfoService.queryDanger(1);
        return RestBean.success(res);
    }
}
