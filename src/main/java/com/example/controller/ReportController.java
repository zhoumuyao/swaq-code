package com.example.controller;

import com.example.entity.Report;
import com.example.service.ReportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Validated
@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Resource
    ReportService service;

    @PostMapping("addReport")
    public String addReport(@RequestParam int id){
        System.out.println("开始处理报告...");
        // 获取当前时间戳
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // 创建 SimpleDateFormat 对象，并设置所需的日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化 Timestamp 对象
        String formattedDate = sdf.format(timestamp);

        // 拆分年月日时分秒
        String[] dateParts = formattedDate.split("[- :]");

        String mes = service.add_report(id, dateParts);
        System.out.println(mes);
        return mes;
    }
}
