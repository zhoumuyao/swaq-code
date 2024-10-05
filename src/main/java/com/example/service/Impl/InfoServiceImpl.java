package com.example.service.Impl;

import com.example.entity.Info;
import com.example.mapper.InfoMapper;
import com.example.service.InfoService;
import com.example.util.imageUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


}
