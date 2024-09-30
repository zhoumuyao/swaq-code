package com.example.controller;

import com.example.entity.Report;
import com.example.service.ReportService;
import com.example.service.riskReportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

@Validated
@RestController
@RequestMapping("/api/riskReport")
public class RiskReportController {
    @Resource
    riskReportService service;

    @PostMapping("outRiskReport")
    public File outRiskReport(@RequestBody Report report){
        System.out.println("开始生成报告...");
        //        System.out.println(mes);
        return service.outRiskReport(report.getId());
    }
}
