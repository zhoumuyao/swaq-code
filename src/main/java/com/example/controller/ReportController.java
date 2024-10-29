package com.example.controller;
import com.example.entity.Report;
import com.example.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.File;
import org.springframework.http.MediaType;


@Validated
@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Resource
    ReportService service;

    @PostMapping("outReport")
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
