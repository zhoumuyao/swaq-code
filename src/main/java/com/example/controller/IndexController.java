package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountUser;
import com.example.service.IndexService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Validated
@RestController
@RequestMapping("/api/index")
public class IndexController {

    @Resource
    IndexService indexService;

    @PostMapping("/online_number")
    public RestBean<Integer> getOnlineNumber(){
        int number = indexService.getOnlineNumber();
        if(number >= 0)
            return RestBean.success(number);
        else
            return RestBean.failure(400);
    }
}
