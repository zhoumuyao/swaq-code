package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import com.example.service.IndexService;
import com.example.service.OnlineUserStatsService;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;

@Validated
@RestController
@RequestMapping("/api/index")
public class IndexController {

    @Resource
    IndexService indexService;

    @Resource
    OnlineUserStatsService onlineUserStatsService;



    @Resource
    AuthorizeService authorizeService;

    @PostMapping("/online_number")
    public RestBean<Long> getOnlineNumber(){
        Duration oneMin = Duration.ofMinutes(1);
        Duration oneHour = Duration.ofHours(1);
        Duration oneDay = Duration.ofDays(1);
        onlineUserStatsService.clear(oneDay);
        Long number = onlineUserStatsService.count();
        if(number >= 0)
            return RestBean.success(number);
        else
            return RestBean.failure(400);
    }

    @PostMapping("/logout")
    public RestBean<String> logout(@RequestParam String username){
        authorizeService.logout(username);
        return RestBean.success("登出成功");
    }




//    @PostMapping("/online_number")
//    public RestBean<Integer> getOnlineNumber(){
//        int number = indexService.getOnlineNumber();
//        if(number >= 0)
//            return RestBean.success(number);
//        else
//            return RestBean.failure(400);
//    }
}
