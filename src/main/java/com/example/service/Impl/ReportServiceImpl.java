package com.example.service.Impl;
import com.example.entity.*;
import com.example.mapper.DisposalMapper;
import com.example.mapper.RiskMapper;
import com.example.mapper.InvestMapper;
import com.example.mapper.CaseMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import com.example.service.ReportService;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;


@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private CaseMapper caseMapper;

    @Resource
    private RiskMapper riskMapper;

    @Resource
    private InvestMapper investMapper;

    @Resource
    DisposalMapper disposalMapper;

    /**
     * 新增报告
     */
    @Override
    public byte[] outReport(int id){
        // 输出文件
        File outFile = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // 报告存储路径
        String FILE_PATH_TEMPLATE = "src/main/resources/reports/%s";
        outFile = new File(String.format(FILE_PATH_TEMPLATE, "案件" + id + "_生物安全案事件处置报告" + ".pdf"));
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
            // headerParagraph 文档标题
            Paragraph headerParagraph = new Paragraph("生物安全案事件处置报告", headerFont);
            ///文字居中
            headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            //将添加到文档中
            document.add(headerParagraph);


            // 4. 添加报告导出日期
            Font titleFont = new Font(baseFont, 15, Font.BOLD, BaseColor.BLACK);
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
            BiologicalCase case_info = caseMapper.selectCase(id);
            String[] case_info_titles = {
                    String.format("1、案件号：%s", case_info.getId()),
                    String.format("2、时间：%s %s", case_info.getDate(), case_info.getTime()),
                    String.format("3、地点：%s%s%s%s", case_info.getProvince(), case_info.getCountry(), case_info.getUrban(), case_info.getDescription()),
                    "4、现场情况：",
                    String.format("伤员人数：%s， 症状信息：%s，影响范围：%s", case_info.getCasualties(), case_info.getSymptomMessage(), case_info.getInfluenceScope()),
            };
            // 添加标题段落
            Paragraph title = new Paragraph("一、生物安全案事件基本信息\n", titleFont);
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
            Risk risk_info = riskMapper.selectRiskPlan(id);
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

            String[] risk_title = {
                    String.format("1、时间：%s %s", risk_info.getDate(), risk_info.getTime()),
                    String.format("2、地点：%s%s%s%s", risk_info.getCountry(), risk_info.getProvince(),  risk_info.getUrban(), risk_info.getDescription()),
                    String.format("%s", personString),
                    String.format("%s", equipmentString),
                    String.format("%s", disposalString),
            };
            // 添加标题段落
            title = new Paragraph("二、生物安全案事件风险评估\n", titleFont);
            title.setSpacingBefore(15);
            document.add(title);
            // 循环添加子段落
            for (String content : risk_title) {
                Paragraph item = new Paragraph(content, contentFont);
                item.setSpacingBefore(5); // 设置段落间距
                document.add(item);
            }
            // 创建一个7列的表格
            PdfPTable table1 = new PdfPTable(7);
            // 设置表格宽度为100%，与前面间隔为5
            table1.setWidthPercentage(100);
            table1.setSpacingBefore(5);
            // 设置列宽（可选，根据需要自定义列宽）
            float[] table1_Widths = new float[]{0.5F, 1, 1, 2, 1, 1, 1};
            table1.setWidths(table1_Widths);

            // 定义表头和数据
            String[][] table1_rows = {{"序号", "采样种类", "采样内容", "检验要求", "快检方法", "快检结果", "检验概率"}};

            if(!disposalObjects.isEmpty()){
                // 动态扩展二维数组以容纳数据
                table1_rows = Arrays.copyOf(table1_rows, disposalObjects.size() + 1);

                // 循环填充数据
                for (int i = 0; i < disposalObjects.size(); i++) {
                    DisposalObject disposalObject = disposalObjects.get(i);
                    table1_rows[i + 1] = new String[]{
                            String.valueOf(i + 1), // 序号，从 1 开始
                            disposalObject.getObjectClass(), // 采样种类
                            disposalObject.getSampleContent(), // 采样内容
                            disposalObject.getSampleRequirement(), // 检验要求
                            disposalObject.getTestMethod(), // 快检方法
                            disposalObject.getResult(), // 快检结果
                            disposalObject.getProbability() // 检验概率
                    };
                }
            }

            // 使用循环添加表格内容
            for (String[] row : table1_rows) {
                for (String cell : row) {
                    table1.addCell(new Phrase(cell, contentFont)); // 使用 Phrase 添加内容
                }
            }
            document.add(table1);

            // 7. 添加【三、生物安全案事件现场处置】
            Invest handle_info = investMapper.queryInvest(id);
            int[] handlePersons = investMapper.selectHandlePerson(id);
            StringBuilder handlePersonsList = new StringBuilder();
            for (int PersonId : handlePersons) {
                String name = investMapper.selectHandlePersonName(PersonId);
                if (handlePersonsList.length() > 0) {
                    handlePersonsList.append(" ");
                }
                handlePersonsList.append(name);
            }


            int[] handleEquipments = investMapper.selectHandleEquipment(id);
            StringBuilder handleEquipmentsList = new StringBuilder();
            for(int EquipmentId : handleEquipments) {
                String name = investMapper.selectHandleEquipmentName(EquipmentId);
                if (handleEquipmentsList.length() > 0) {
                    handleEquipmentsList.append(" ");
                }
                handleEquipmentsList.append(name);
            }

            String[] handle_title = {
                    String.format("1、时间：%s %s", handle_info.getDate(), handle_info.getTime()),
                    String.format("2、地点：%s%s%s%s", case_info.getProvince(), case_info.getCountry(), case_info.getUrban(), case_info.getDescription()),
                    String.format("3、人员：%s", handlePersonsList),
                    String.format("4、装备：%s", handleEquipmentsList),
                    "5、现场信息记录：",
            };
            title = new Paragraph("三、生物安全案事件现场处置\n", titleFont);
            title.setSpacingBefore(15);
            document.add(title);
            // 循环添加子段落
            for (String content : handle_title) {
                Paragraph item = new Paragraph(content, contentFont);
                item.setSpacingBefore(5); // 设置段落间距
                document.add(item);
            }

            // 创建一个2行4列的表格
            PdfPTable table2_1 = new PdfPTable(4); // 4 列
            // 设置表格宽度为100%，与前面间隔为5
            table2_1.setWidthPercentage(100);
            table2_1.setSpacingBefore(5);
            // 设置列宽（可选，根据需要自定义列宽）
            float[] table2_1_Widths = new float[]{1, 1, 1, 1};
            table2_1.setWidths(table2_1_Widths);

            // 定义表头和数据
            String[][] table2_1_rows = {
                {
                    String.format("空气：%s", handle_info.getAirQuality()),
                    String.format("水质：", handle_info.getWater()),
                    String.format("土壤：%s", handle_info.getSoil()),
                    String.format("天气：%s", handle_info.getWeather()),
                }, {
                    String.format("温度：%s℃", handle_info.getTemperature()),
                    String.format("湿度：%s%s", handle_info.getHumi(), "%"),
                    String.format("风向：%s", handle_info.getWindDirection()),
                    String.format("风速：%sm/s", handle_info.getWindSpeed()),
                },
            };

            // 使用循环添加表格内容
            for (String[] row : table2_1_rows) {
                for (String cell : row) {
                    table2_1.addCell(new Phrase(cell, contentFont)); // 使用 Phrase 添加内容
                }
            }

            document.add(table2_1);

            // 创建一个1行2列的表格
            PdfPTable table2_2 = new PdfPTable(2); // 2 列
            // 设置表格宽度为100%，与前面无间隔
            table2_2.setWidthPercentage(100);
            // 设置列宽（可选，根据需要自定义列宽）
            float[] table2_2_Widths = new float[]{1, 1};
            table2_2.setWidths(table2_2_Widths);
            String[] table2_2_row = {
                    String.format("人口密度:%s", handle_info.getPersonelDensity()),
                    String.format("人员活动:%s", handle_info.getGather())
            };

            for (String cell : table2_2_row) {
                table2_2.addCell(new Phrase(cell, contentFont)); // 使用 Phrase 添加内容
            }
            // 将表格添加到文档中
            document.add(table2_2);

            // 添加标题段落
            title = new Paragraph("划定封锁区域：\n", contentFont);
            title.setSpacingBefore(5);
            document.add(title);

            title = new Paragraph("6、现场详细勘查：\n", contentFont);
            title.setSpacingBefore(5);
            document.add(title);

            // 7. 添加【四、致死致伤检验鉴定】

            title = new Paragraph("四、致死致伤检验鉴定\n", titleFont);
            title.setSpacingBefore(15);
            document.add(title);

            title = new Paragraph("1、实验室检测结果：\n", contentFont);
            title.setSpacingBefore(5);
            document.add(title);

            document.add(table1);

            String[] identify_title = {
                    "2、尸体检验报告：",
                    "（1）基本情况：",
                    "    委托人：",
                    "    委托事项：",
                    "    受理时间：",
                    "    鉴定材料：",
                    "    鉴定日期：",
                    "    鉴定地点：",
                    "    在场人员：",
                    "    被鉴定人：",
            };

            for (String content : identify_title) {
                Paragraph item = new Paragraph(content, contentFont);
                item.setSpacingBefore(5); // 设置段落间距
                document.add(item);
            }

            title = new Paragraph("处理人员签字：\n", contentFont);
            title.setSpacingBefore(5);
            document.add(title);

            // 创建一个3行2列的表格
            PdfPTable table5 = new PdfPTable(2); // 2 列
            // 设置表格宽度为60%，与前面无间隔
            table5.setWidthPercentage(85);
            table5.setSpacingBefore(5);
            // 设置列宽（可选，根据需要自定义列宽）
            float[] table5_Widths = new float[]{1, 3};
            table5.setWidths(table5_Widths);
            String[][] table5_row = {
                    {
                        "风险评估人员：",""
                    },
                    {
                        "现场处置人员：",""
                    },
                    {
                        "检验鉴定人员：",""
                    }
            };

            for (String[] row : table5_row) {
                int columnIndex = 0; // 计数器，用于跟踪当前单元格的索引
                for (String cell_c : row) {
                    PdfPCell cell = new PdfPCell(new Phrase(cell_c, contentFont));
                    if (columnIndex == 1) { // 判断是否为第二个单元格（索引从0开始，所以第二个单元格的索引是1）
                        // 在这里添加对第二个单元格的处理
                        cell.disableBorderSide(13);//只保留下边框
                    }
                    else{
                        cell.disableBorderSide(15);//全部隐藏边框
                    }
                    cell.setFixedHeight(25f); // 设置行高为50
                    table5.addCell(cell);
                    columnIndex++; // 增加索引
                }
            }
            // 将表格添加到文档中
            document.add(table5);


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
