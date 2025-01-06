package com.example.controller;

import com.example.entity.BiologyInfo;
import com.example.entity.DisposalObject;
import com.example.entity.RestBean;
import com.example.service.BiologyInfoService;
import com.example.service.DisposalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/biologyInfo")
public class BiologyInfoController {
    @Resource
    BiologyInfoService biologyInfoService;

    @Resource
    DisposalService disposalService;

    //查询危险名称对应的生物危险信息
    //若返回信息为空代表查询失败
    @RequestMapping("/searchInfo")
    public RestBean<BiologyInfo> searchInfo(@RequestParam String dangerName){
        BiologyInfo info;
        info = biologyInfoService.searchInfo(dangerName);
        return RestBean.success(info);
    }

    //查询当前处置对象中排序第一的快检结果
    @RequestMapping("/find_dangername")
    public RestBean<String> findDangerName(@RequestParam Integer id){
        List<DisposalObject> disposalObjectList = disposalService.searchDisposal(id);
        String dangerName=null;
        int maxId = Integer.MAX_VALUE;
        for(DisposalObject d :disposalObjectList){
            if(d.getId()<maxId){
                dangerName = d.getResult();
            }
        }
        return RestBean.success(dangerName);
    }
}
