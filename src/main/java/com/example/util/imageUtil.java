package com.example.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class imageUtil {


    public static void saveImage(MultipartFile image, String directoryPath) {
        // 检查目录是否存在，如果不存在则创建
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 获取文件的名称
        String fileName = image.getOriginalFilename();

        // 创建目标文件
        File targetFile = new File(directory, fileName);

        // 将图片写入目标文件
        try (InputStream inputStream = image.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(targetFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            // 将图片数据写入目标文件
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void removeImage() {

    }
}
