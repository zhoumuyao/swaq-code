package com.example.controller;

import com.example.entity.Report;
import com.example.service.ReportService;
import com.example.service.riskReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("report")
    public ResponseEntity<byte[]> outReport(@RequestBody Report report){
        System.out.println("开始生成报告...");
        try {
            byte[] pdfContent = service.outReport(report.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Corrected MediaType reference
            headers.setContentDispositionFormData("attachment", "document.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);
        } catch (Exception e) {
            // 错误处理
            return ResponseEntity.internalServerError().build();
        }
    }
}
