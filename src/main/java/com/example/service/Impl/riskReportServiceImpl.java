package com.example.service.Impl;

import com.example.entity.*;
import com.example.mapper.DisposalMapper;
import com.example.mapper.ReportMapper;
import com.example.mapper.RiskMapper;
import com.example.service.riskReportService;
import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class riskReportServiceImpl implements riskReportService {
    @Resource
    private ReportMapper mapper;

    @Resource
    private RiskMapper riskMapper;

    @Resource
    DisposalMapper disposalMapper;
    /**
     * 新增报告
     */
    @Override
    public File outRiskReport(int id){
        // 输出文件
        File outFile = null;

        try {
//            String FILE_PATH_TEMPLATE = System.getProperty("java.io.tmpdir") + "/tempdf/%s";
            String FILE_PATH_TEMPLATE = System.getProperty("user.home") + "\\Desktop" + "/tempdf/%s";
            //生成临时文件位置，存储到 java.io.tmpdir 下
            outFile = new File(String.format(FILE_PATH_TEMPLATE, id + "生物安全案事件处置报告.pdf"));
            //如果不存在临时文件夹，则创建文件夹
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            System.out.println("临时文件所在位置：——" + outFile.getPath());
            FileOutputStream outputStream = new FileOutputStream(outFile);
            Rectangle rectangle = new Rectangle(PageSize.A4);
            //Rectangle rectangle = PageSize.A4.rotate();

            //1.创建文档 并设置四周边距
            Document document = new Document(rectangle, 40, 40, 40, 40);
            //2.新建pdf实例 输出到本地文件目录
            //PDFWriter可以将文档存成PDF文件；HtmlWriter可以将文档存成html文件
            PdfWriter.getInstance(document, outputStream);
            //3.打开文档 无论输出到本地还是 页面都要打开文档
            document.open();
            // 设置pdf的基础样式；参数1:(1)使用iText中的字体,字体类行为宋体，（2）使用资源字体classpath：/SIMYOU.TTF （3）使用windows系统字体C:/Windows/Fonts/SIMYOU.TTF
            BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont, AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
            // 文档标题对应的样式 参数1：相当于字体（宋体） 参数2：字体大小 参数3：是否加粗，斜体 参数4：字体颜色
            Font headerFont = new Font(baseFont, 30, Font.NORMAL, BaseColor.BLACK);
            // headerParagraph  文档标题
            Paragraph headerParagraph = new Paragraph("生物安全案事件处置报告", headerFont);
            ///文字居中
            headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            //将添加到文档中
            document.add(headerParagraph);


            // 4. 添加报告导出日期
            Font contentFont = new Font(baseFont, 15, Font.NORMAL, BaseColor.BLACK);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String formattedDate = sdf.format(timestamp);
            String contentText = String.format("报告生成时间：%s", formattedDate);
            Paragraph contentParagraph = new Paragraph(contentText, contentFont);
            contentParagraph.setSpacingBefore(10); // 设置段落与标题之间的距离
            contentParagraph.setAlignment(Paragraph.ALIGN_LEFT); // 左对齐
            document.add(contentParagraph);


            // 6. 添加【二、生物安全案事件风险评估】
            java.util.List<DisposalObject> disposalObjects = disposalMapper.searchDisposal(id);
            BiologicalCase risk_info = mapper.select_caseById(id);
            int[] riskPersons = riskMapper.selectRiskPerson(id);
            int[] riskEquipments = riskMapper.selectRiskEquipment(id);
            java.util.List<Person> listRiskPerson = riskMapper.selectPersonList();
            List<Equipment> listRiskEquipment = riskMapper.selectEquipmentList();

            // 将 int 数组转换为 Set 以提高搜索效率
            Set<Integer> riskPersonIds = new HashSet<>();
            for (int personId : riskPersons) {
                riskPersonIds.add(personId);
            }

            Set<Integer> riskEquipmentIds = new HashSet<>();
            for (int equipmentId : riskEquipments) {
                riskEquipmentIds.add(equipmentId);
            }

            // 过滤 listRiskPerson，移除不包含在 riskPersons 数组中的 Person 对象
            listRiskPerson.removeIf(person -> !riskPersonIds.contains(person.getId()));
            StringBuilder personString = new StringBuilder("3、人员：");

            if (!listRiskPerson.isEmpty()){
                for(Person person : listRiskPerson){
                    personString.append(person.getName()).append(" ");
                }
            }

            listRiskEquipment.removeIf(equipment -> !riskEquipmentIds.contains(equipment.getId()));
            StringBuilder equipmentString = new StringBuilder("4、装置：");
            if (!listRiskEquipment.isEmpty()){
                for(Equipment equipment : listRiskEquipment){
                    equipmentString.append(equipment.getName()).append(" ");
                }
            }

            StringBuilder disposalString = new StringBuilder("5、样本信息：");
            if(!disposalObjects.isEmpty()){
                for (DisposalObject disposalObject : disposalObjects){
                    disposalString.append("采样种类：").append(disposalObject.getObjectClass()).append("   采样内容：").append(disposalObject.getSampleContent());
                    disposalString.append("   快检方法：").append(disposalObject.getTestMethod()).append("   快检结果：").append(disposalObject.getResult());
                    disposalString.append("   检测概率：").append(disposalObject.getProbability()).append("   采样要求：").append(disposalObject.getSampleRequirement()).append("\n");
                }
            }

            String[] risk_title = {
                    String.format("1、时间：%s %s", risk_info.getDate(), risk_info.getTime()),
                    String.format("2、地点：%s%s%s%s", risk_info.getCountry(), risk_info.getProvince(),  risk_info.getUrban(), risk_info.getDescription()),
                    String.format("%s", personString),
                    String.format("%s", equipmentString),
                    String.format("%s", disposalString),
            };
            // 添加标题段落
            Paragraph title = new Paragraph("生物安全案事件风险评估\n", contentFont);
            title.setSpacingBefore(15);
            document.add(title);
            // 循环添加子段落
            for (String content : risk_title) {
                Paragraph item = new Paragraph(content, contentFont);
                item.setSpacingBefore(5); // 设置段落间距
                document.add(item);
            }

            // x. 添加文件日期
            String Date ="日期：     年    月    日";
            Paragraph footerDate = new Paragraph(Date, contentFont);
            footerDate.setSpacingBefore(10);
            footerDate.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(footerDate);

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile;
    }

    @Override
    public byte[] outReport(int id){
        // 输出文件
        File outFile = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // 报告存储路径
        String FILE_PATH_TEMPLATE = "src/main/resources/reports/%s";
        outFile = new File(String.format(FILE_PATH_TEMPLATE, "案件" + id + "_生物安全案事件风险评估报告" + ".pdf"));
        // 如果不存在 reports 文件夹，则创建文件夹
        File reportsDir = new File("src/main/resources/reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        System.out.println("报告文件即将存储在：" + outFile.getPath());
        try {
            //  String FILE_PATH_TEMPLATE = System.getProperty("user.home") + "\\Desktop" + "/tempdf/%s";
            //  //生成临时文件位置，存储到 java.io.tmpdir 下
            //  outFile = new File(String.format(FILE_PATH_TEMPLATE, id + "生物安全案事件处置报告.pdf"));
            //  /如果不存在临时文件夹，则创建文件夹
            //  if (!outFile.getParentFile().exists()) {
            //      outFile.getParentFile().mkdirs();
            //   }
            FileOutputStream outputStream = new FileOutputStream(outFile);
            Rectangle rectangle = new Rectangle(PageSize.A4);

            //1.创建文档 并设置四周边距
            Document document = new Document(rectangle, 40, 40, 40, 40);
            //2.新建pdf实例 输出到本地文件目录
            //PDFWriter可以将文档存成PDF文件；HtmlWriter可以将文档存成html文件
            PdfWriter.getInstance(document, byteArrayOutputStream);
            PdfWriter.getInstance(document, outputStream);
            //3.打开文档 无论输出到本地还是 页面都要打开文档
            document.open();
            // 设置pdf的基础样式；参数1:(1)使用iText中的字体,字体类行为宋体，（2）使用资源字体classpath：/SIMYOU.TTF （3）使用windows系统字体C:/Windows/Fonts/SIMYOU.TTF
            BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont, AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
            // 文档标题对应的样式 参数1：相当于字体（宋体） 参数2：字体大小 参数3：是否加粗，斜体 参数4：字体颜色
            Font headerFont = new Font(baseFont, 30, Font.NORMAL, BaseColor.BLACK);
            // headerParagraph  文档标题
            Paragraph headerParagraph = new Paragraph("生物安全案事件风险评估报告", headerFont);
            ///文字居中
            headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            //将添加到文档中
            document.add(headerParagraph);


            // 4. 添加报告导出日期
            Font contentFont = new Font(baseFont, 15, Font.NORMAL, BaseColor.BLACK);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String formattedDate = sdf.format(timestamp);
            String contentText = String.format("报告生成时间：%s", formattedDate);
            Paragraph contentParagraph = new Paragraph(contentText, contentFont);
            contentParagraph.setSpacingBefore(10); // 设置段落与标题之间的距离
            contentParagraph.setAlignment(Paragraph.ALIGN_LEFT); // 左对齐
            document.add(contentParagraph);

            // 5. 添加【一、生物安全案事件基本信息】
            BiologicalCase case_info = mapper.select_caseById(id);
            String[] case_info_titles = {
                    String.format("1、案件号：%s", case_info.getId()),
                    String.format("2、时间：%s %s", case_info.getDate(), case_info.getTime()),
                    String.format("3、地点：%s%s%s%s", case_info.getProvince(), case_info.getCountry(), case_info.getUrban(), case_info.getDescription()),
                    String.format("4、现场情况：(%s, %s)", case_info.getLongitude(), case_info.getLatitude()),
            };
            // 添加标题段落
            Paragraph title = new Paragraph("一、生物安全案事件基本信息\n", contentFont);
            title.setSpacingBefore(15);
            document.add(title);
            // 循环添加子段落
            for (String content : case_info_titles) {
                Paragraph item = new Paragraph(content, contentFont);
                item.setSpacingBefore(5); // 设置段落间距
                document.add(item);
            }

            // 6. 添加【二、生物安全案事件风险评估】
            List<DisposalObject> disposalObjects = disposalMapper.searchDisposal(id);
            BiologicalCase risk_info = mapper.select_caseById(id);
            int[] riskPersons = riskMapper.selectRiskPerson(id);
            int[] riskEquipments = riskMapper.selectRiskEquipment(id);
            List<Person> listRiskPerson = riskMapper.selectPersonList();
            List<Equipment> listRiskEquipment = riskMapper.selectEquipmentList();

            // 将 int 数组转换为 Set 以提高搜索效率
            Set<Integer> riskPersonIds = new HashSet<>();
            for (int personId : riskPersons) {
                riskPersonIds.add(personId);
            }

            Set<Integer> riskEquipmentIds = new HashSet<>();
            for (int equipmentId : riskEquipments) {
                riskEquipmentIds.add(equipmentId);
            }

            // 过滤 listRiskPerson，移除不包含在 riskPersons 数组中的 Person 对象
            listRiskPerson.removeIf(person -> !riskPersonIds.contains(person.getId()));
            StringBuilder personString = new StringBuilder("3、人员：");

            if (!listRiskPerson.isEmpty()){
                for(Person person : listRiskPerson){
                    personString.append(person.getName()).append(" ");
                }
            }

            listRiskEquipment.removeIf(equipment -> !riskEquipmentIds.contains(equipment.getId()));
            StringBuilder equipmentString = new StringBuilder("4、装置：");
            if (!listRiskEquipment.isEmpty()){
                for(Equipment equipment : listRiskEquipment){
                    equipmentString.append(equipment.getName()).append(" ");
                }
            }

            StringBuilder disposalString = new StringBuilder("5、样本信息：");
            if(!disposalObjects.isEmpty()){
                for (DisposalObject disposalObject : disposalObjects){
                    disposalString.append("采样种类：").append(disposalObject.getObjectClass()).append("  采样内容：").append(disposalObject.getSampleContent());
                    disposalString.append("  快检方法：").append(disposalObject.getTestMethod()).append("  快检结果：").append(disposalObject.getResult());
                    disposalString.append("  检测概率：").append(disposalObject.getProbability()).append("  采样要求：").append(disposalObject.getSampleRequirement()).append("\n");
                }
            }

            String[] risk_title = {
                    String.format("1、时间：%s %s", risk_info.getDate(), risk_info.getTime()),
                    String.format("2、地点：%s%s%s%s", risk_info.getCountry(), risk_info.getProvince(),  risk_info.getUrban(), risk_info.getDescription()),
                    String.format("%s", personString),
                    String.format("%s", equipmentString),
                    String.format("%s", disposalString),
            };
            // 添加标题段落
            title = new Paragraph("二、生物安全案事件风险评估\n", contentFont);
            title.setSpacingBefore(15);
            document.add(title);
            // 循环添加子段落
            for (String content : risk_title) {
                Paragraph item = new Paragraph(content, contentFont);
                item.setSpacingBefore(5); // 设置段落间距
                document.add(item);
            }

            // x. 添加文件日期
            String Date ="日期：     年    月    日";
            Paragraph footerDate = new Paragraph(Date, contentFont);
            footerDate.setSpacingBefore(10);
            footerDate.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(footerDate);

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
