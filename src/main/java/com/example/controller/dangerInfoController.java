package com.example.controller;

import com.example.entity.DangerInfo;
import com.example.entity.Invest;
import com.example.entity.RestBean;
import com.example.entity.vo.DangerVo;
import com.example.service.DangerInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/dangerInfo")
public class dangerInfoController {
    @Resource
    DangerInfoService dangerInfoService;

    @GetMapping("queryDanger")
    public RestBean<DangerInfo> queryDanger(@RequestParam("id") int caseId){
        DangerInfo res = dangerInfoService.queryDanger(caseId);
        if (res == null) {
            // 如果结果为空，返回 404 Not Found
            return RestBean.failure(404);
        }
        return RestBean.success(res);
    }

    @PostMapping("updateDanger")
    public RestBean<String> updateDanger(@RequestBody DangerVo dangerVo){
        String res = dangerInfoService.updateDanger(dangerVo);
        return RestBean.success(res);
    }
}
