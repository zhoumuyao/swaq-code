package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/infoInput")
public class InfoInputController {

    @Resource
    InfoService infoService;
    @PostMapping("createInfo")
    public RestBean<String> createInfo(@RequestParam("id") int id,
                                       @RequestParam("name") String name){
        String res = infoService.createInfo(id,name);
        return RestBean.success(res);

    }

}
