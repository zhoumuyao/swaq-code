package com.example.service.Impl;
import com.example.entity.BiologicalCase;
import com.example.entity.Report;
import com.example.mapper.ReportMapper;
import com.example.service.ReportService;
import lombok.ToString;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper mapper;

    /**
     * 新增报告
     */
    @Override
    public String add_report(int id, String[] dateParts) {
        if(dateParts == null){
            return null;
        }
        
        // 提取各个部分
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        String hour = dateParts[3];
        String minute = dateParts[4];
        String second = dateParts[5];

        BiologicalCase locationCheck = mapper.select_caseById(id);
        if(locationCheck != null){
            StringBuilder lo = new StringBuilder();
            lo.append(locationCheck.getProvince());
            lo.append(locationCheck.getUrban());
            lo.append(locationCheck.getDescription());
            lo.append("(");
            lo.append(locationCheck.getLongitude());
            lo.append(",");
            lo.append(locationCheck.getLatitude());
            lo.append(")");
            int caseCheck = mapper.insert_report(id, year, month, day, hour, minute, second, lo.toString());
            if(caseCheck <= 0){
                return null;
            }
            Report report = mapper.select_reportById(id);

            replacePlaceholderInWord(report);

            return "报告已生成";
        }
        else{
            String errorMes = "不存在此案件id:" + id;
            return errorMes;
        }


    }

    public void replacePlaceholderInWord(Report report) {
        String filePath = "src/main/resources/origin.docx";
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("{year}", report.getYear());
            placeholders.put("{month}", report.getMonth());
            placeholders.put("{day}", report.getDay());
            placeholders.put("{hour}", report.getHour());
            placeholders.put("{minute}", report.getMinute());
            placeholders.put("{second}", report.getSecond());
            placeholders.put("{locations}", report.getLocations());

            // 遍历文档中的段落和运行
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        // 替换所有占位符
                        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                            if (text.contains(entry.getKey())) {
                                text = text.replace(entry.getKey(), entry.getValue());
                            }
                        }
                        run.setText(text, 0);
                    }
                }
            }

            // 写入输出文件
            try (FileOutputStream fos = new FileOutputStream("src/main/resources/output.docx")) {
                document.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
