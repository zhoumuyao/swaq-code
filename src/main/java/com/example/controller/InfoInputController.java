package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/infoInput")
public class InfoInputController {

    @Resource
    InfoService infoService;
    @PostMapping("/createInfo")
    public RestBean<String> createInfo(@RequestParam("id") int id,
                                       @RequestParam(value = "file1", required = false) MultipartFile file1,
                                       @RequestParam(value = "file2", required = false) MultipartFile file2,
                                       @RequestParam(value = "file3", required = false) MultipartFile file3,
                                       @RequestParam(value = "file4", required = false) MultipartFile file4,
                                       @RequestParam(value = "file5", required = false) MultipartFile file5,
                                       @RequestParam(value = "file6", required = false) MultipartFile file6,
                                       @RequestParam(value = "file7", required = false) MultipartFile file7,
                                       @RequestParam(value = "file8", required = false) MultipartFile file8,
                                       @RequestParam(value = "file9", required = false) MultipartFile file9,
                                       @RequestParam(value = "file10", required = false) MultipartFile file10,
                                       @RequestParam(value = "file11", required = false) MultipartFile file11,
                                       @RequestParam(value = "file12", required = false) MultipartFile file12,
                                       @RequestParam(value = "file13", required = false) MultipartFile file13,
                                       @RequestParam(value = "file14", required = false) MultipartFile file14,
                                       @RequestParam(value = "file15", required = false) MultipartFile file15,
                                       @RequestParam(value = "file16", required = false) MultipartFile file16,
                                       @RequestParam(value = "file17", required = false) MultipartFile file17,
                                       @RequestParam(value = "file18", required = false) MultipartFile file18,
                                       @RequestParam(value = "file19", required = false) MultipartFile file19
                                       ){
        String res = infoService.createInfo(id,file1,file2,file3,file4,file5,file6,file7,file8,file9,file10,file11,file12,file13,file14,file15,file16,file17,file18,file19);
        return RestBean.success(res);
    }

    @PostMapping("/queryInfo")
    public RestBean<Map<Integer,String>> queryInfo(@RequestParam("id") int id){
        Map<Integer,String> res = infoService.queryInfo(id);
        return RestBean.success(res);
    }
}
