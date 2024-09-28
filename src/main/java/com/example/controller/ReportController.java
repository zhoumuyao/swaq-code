package com.example.controller;
import com.example.entity.Report;
import com.example.service.ReportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.File;

@Validated
@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Resource
    ReportService service;

    @PostMapping("outReport")
    public File outReport(@RequestBody Report report){
        System.out.println("开始生成报告...");
        //        System.out.println(mes);
        return service.outReport("abtt", report.getId());
    }
}
