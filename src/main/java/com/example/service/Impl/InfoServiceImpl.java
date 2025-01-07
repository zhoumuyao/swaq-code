package com.example.service.Impl;

import com.example.entity.Info;
import com.example.mapper.InfoMapper;
import com.example.service.InfoService;
import com.example.util.imageUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Resource
    InfoMapper infoMapper;

    @Override
    public String createInfo(int id,
                             MultipartFile file1,
                             MultipartFile file2,
                             MultipartFile file3,
                             MultipartFile file4,
                             MultipartFile file5,
                             MultipartFile file6,
                             MultipartFile file7,
                             MultipartFile file8,
                             MultipartFile file9,
                             MultipartFile file10,
                             MultipartFile file11,
                             MultipartFile file12,
                             MultipartFile file13,
                             MultipartFile file14,
                             MultipartFile file15,
                             MultipartFile file16,
                             MultipartFile file17,
                             MultipartFile file18,
                             MultipartFile file19) {

        String folderPath = "src/main/resources/static/info/" + id;

        // 创建文件对象
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();  // 创建目录及其父目录
        }

        // 定义文件数组以简化保存
        MultipartFile[] files = {file1, file2, file3, file4, file5, file6,
                file7, file8, file9, file10, file11,
                file12, file13, file14, file15, file16,
                file17, file18, file19};

//        List<String> filePaths = new ArrayList<>();  // 存储文件路径
        Map<Integer,String> filePaths = new HashMap<>();
        // 遍历文件数组并保存
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename(); // 获取照片名
                String filePath = folderPath + "/" + (i + 1);
                imageUtil.saveImage(file, filePath);  // 保存文件
                filePaths.put(i+1,"/info/"+ id +"/" + (i + 1) + "/" + fileName);  // 添加文件路径到列表
            }
        }
        String file1path = filePaths.get(1);
        String file2path = filePaths.get(2);
        String file3path = filePaths.get(3);
        String file4path = filePaths.get(4);
        String file5path = filePaths.get(5);
        String file6path = filePaths.get(6);
        String file7path = filePaths.get(7);
        String file8path = filePaths.get(8);
        String file9path = filePaths.get(9);
        String file10path = filePaths.get(10);
        String file11path = filePaths.get(11);
        String file12path = filePaths.get(12);
        String file13path = filePaths.get(13);
        String file14path = filePaths.get(14);
        String file15path = filePaths.get(15);
        String file16path = filePaths.get(16);
        String file17path = filePaths.get(17);
        String file18path = filePaths.get(18);
        String file19path = filePaths.get(19);

        Info info = infoMapper.containInfoById(id);
        if (info == null){
            infoMapper.createInfo(id,file1path,file2path,file3path,file4path,file5path,file6path,file7path,file8path,file9path,file10path,file11path,file12path,file13path,file14path,file15path,file16path,file17path,file18path,file19path);  // 假设该方法接受一个 List<String> 类型的参数
        }
        else {
            infoMapper.updateInfo(id,file1path,file2path,file3path,file4path,file5path,file6path,file7path,file8path,file9path,file10path,file11path,file12path,file13path,file14path,file15path,file16path,file17path,file18path,file19path);  // 假设该方法接受一个 List<String> 类型的参数
        }

        return "成功存入";
    }

    @Override
    public Map<Integer, String> queryInfo(int id) {
        Map<Integer, String> map = new HashMap<>();
        Info info = infoMapper.queryInfo(id);
        map.put(1, info.getFile1path() != null ? info.getFile1path() : "");
        map.put(2, info.getFile2path() != null ? info.getFile2path() : "");
        map.put(3, info.getFile3path() != null ? info.getFile3path() : "");
        map.put(4, info.getFile4path() != null ? info.getFile4path() : "");
        map.put(5, info.getFile5path() != null ? info.getFile5path() : "");
        map.put(6, info.getFile6path() != null ? info.getFile6path() : "");
        map.put(7, info.getFile7path() != null ? info.getFile7path() : "");
        map.put(8, info.getFile8path() != null ? info.getFile8path() : "");
        map.put(9, info.getFile9path() != null ? info.getFile9path() : "");
        map.put(10, info.getFile10path() != null ? info.getFile10path() : "");
        map.put(11, info.getFile11path() != null ? info.getFile11path() : "");
        map.put(12, info.getFile12path() != null ? info.getFile12path() : "");
        map.put(13, info.getFile13path() != null ? info.getFile13path() : "");
        map.put(14, info.getFile14path() != null ? info.getFile14path() : "");
        map.put(15, info.getFile15path() != null ? info.getFile15path() : "");
        map.put(16, info.getFile16path() != null ? info.getFile16path() : "");
        map.put(17, info.getFile17path() != null ? info.getFile17path() : "");
        map.put(18, info.getFile18path() != null ? info.getFile18path() : "");
        map.put(19, info.getFile19path() != null ? info.getFile19path() : "");
        return map;
    }

    @Override
    public File outReport(int id){
        return null;
    }
}
//    public File outReport(int id) {
//        File outFile = null;
//
//        try {
////            String FILE_PATH_TEMPLATE = System.getProperty("java.io.tmpdir") + "/tempdf/%s";
//            String FILE_PATH_TEMPLATE = System.getProperty("user.home") + "\\Desktop" + "/tempdf/%s";
//            //生成临时文件位置，存储到 java.io.tmpdir 下
//            outFile = new File(String.format(FILE_PATH_TEMPLATE, id + "生物安全案事件处置报告.pdf"));
//            //如果不存在临时文件夹，则创建文件夹
//            if (!outFile.getParentFile().exists()) {
//                outFile.getParentFile().mkdirs();
//            }
//            System.out.println("临时文件所在位置：——" + outFile.getPath());
//            FileOutputStream outputStream = new FileOutputStream(outFile);
//            Rectangle rectangle = new Rectangle(PageSize.A4);
//            //Rectangle rectangle = PageSize.A4.rotate();
//
//            //1.创建文档 并设置四周边距
//            Document document = new Document(rectangle, 40, 40, 40, 40);
//            //2.新建pdf实例 输出到本地文件目录
//            //PDFWriter可以将文档存成PDF文件；HtmlWriter可以将文档存成html文件
//            PdfWriter.getInstance(document, outputStream);
//            //3.打开文档 无论输出到本地还是 页面都要打开文档
//            document.open();
//            // 设置pdf的基础样式；参数1:(1)使用iText中的字体,字体类行为宋体，（2）使用资源字体classpath：/SIMYOU.TTF （3）使用windows系统字体C:/Windows/Fonts/SIMYOU.TTF
//            BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont, AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
//            // 文档标题对应的样式 参数1：相当于字体（宋体） 参数2：字体大小 参数3：是否加粗，斜体 参数4：字体颜色
//            Font headerFont = new Font(baseFont, 30, Font.NORMAL, BaseColor.BLACK);
//            // headerParagraph  文档标题
//            Paragraph headerParagraph = new Paragraph("生物安全案事件处置报告", headerFont);
//            ///文字居中
//            headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);
//            //将添加到文档中
//            document.add(headerParagraph);
//
//
//            // 4. 添加报告导出日期
//            Font contentFont = new Font(baseFont, 15, Font.NORMAL, BaseColor.BLACK);
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//            String formattedDate = sdf.format(timestamp);
//            String contentText = String.format("报告生成时间：%s", formattedDate);
//            Paragraph contentParagraph = new Paragraph(contentText, contentFont);
//            contentParagraph.setSpacingBefore(10); // 设置段落与标题之间的距离
//            contentParagraph.setAlignment(Paragraph.ALIGN_LEFT); // 左对齐
//            document.add(contentParagraph);
//
//            Invest handle_info = mapper.select_investById(id);
//            String[] handle_title = {
//                    String.format("1、时间：%s %s", handle_info.getDate(), handle_info.getTime()),
//                    String.format("2、地点：%s%s%s%s", case_info.getProvince(), case_info.getCountry(), case_info.getUrban(), case_info.getDescription()),
//                    String.format("3、人员：", "xxx、xx"),
//                    "4、装备：",
//                    "5、现场信息记录：",
//            };
//            // 添加标题段落
//            title = new Paragraph("三、生物安全案事件现场处置\n", contentFont);
//            title.setSpacingBefore(15);
//            document.add(title);
//            // 循环添加子段落
//            for (String content : handle_title) {
//                Paragraph item = new Paragraph(content, contentFont);
//                item.setSpacingBefore(5); // 设置段落间距
//                document.add(item);
//            }
//
//            // 创建一个2行4列的表格
//            PdfPTable table2_1 = new PdfPTable(4); // 4 列
//            // 设置表格宽度为100%，与前面间隔为5
//            table2_1.setWidthPercentage(100);
//            table2_1.setSpacingBefore(5);
//            // 设置列宽（可选，根据需要自定义列宽）
//            float[] table2_1_Widths = new float[]{1, 1, 1, 1};
//            table2_1.setWidths(table2_1_Widths);
//
//            // 定义表头和数据
//            String[][] table2_1_rows = {
//                    {
//                            String.format("空气：%s", handle_info.getAirQuality()),
//                            String.format("水质：", handle_info.getWater()),
//                            String.format("土壤：%s", handle_info.getSoil()),
//                            String.format("天气：%s", handle_info.getWeather()),
//                    }, {
//                    String.format("温度：%s℃", handle_info.getTemperature()),
//                    String.format("湿度：%s%s", handle_info.getHumi(), "%"),
//                    String.format("风向：%s", handle_info.getWindDirection()),
//                    String.format("风速：%sm/s", handle_info.getWindSpeed()),
//            },
//            };
//
//            // 使用循环添加表格内容
//            for (String[] row : table2_1_rows) {
//                for (String cell : row) {
//                    table2_1.addCell(new Phrase(cell, contentFont)); // 使用 Phrase 添加内容
//                }
//            }
//
//            document.add(table2_1);
//
//            // 创建一个1行2列的表格
//            PdfPTable table2_2 = new PdfPTable(2); // 2 列
//            // 设置表格宽度为100%，与前面无间隔
//            table2_2.setWidthPercentage(100);
//            // 设置列宽（可选，根据需要自定义列宽）
//            float[] table2_2_Widths = new float[]{1, 1};
//            table2_2.setWidths(table2_2_Widths);
//            String[] table2_2_row = {
//                    String.format("人口密度:%s", handle_info.getPersonelDensity()),
//                    String.format("人员活动:%s", handle_info.getGather())
//            };
//
//            for (String cell : table2_2_row) {
//                table2_2.addCell(new Phrase(cell, contentFont)); // 使用 Phrase 添加内容
//            }
//            // 将表格添加到文档中
//            document.add(table2_2);
//    } catch (DocumentException e) {
//            throw new RuntimeException(e);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
