package com.example.service.Impl;

import com.example.entity.*;
import com.example.mapper.RiskMapper;
import com.example.service.RiskService;

import java.io.File;
import java.util.Collections;

import com.example.util.imageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RiskServiceImpl implements RiskService {

    @Resource
    RiskMapper mapper;
    @Override
    public String createPlan(Risk riskPlan) {
        if(riskPlan == null){
            return null;
        }
        if(mapper.createPlan(riskPlan) <= 0){
            return null;
        }
        //mapper.addPerson(riskPlan.getId(), riskPlan.getPerson().e.getId());
        //mapper.addEquipment(riskPlan.getId(), riskPlan.getEquipment().getId());
        return "添加成功";
    }

    @Override
    public String updatePlan(Risk riskPlan) {
        if(riskPlan == null){
            return null;
        }
        if(mapper.updatePlan(riskPlan) <= 0){
            return null;
        }
        return "修改成功";
    }

    @Override
    public String addNewriskPerson(int id,String name){
        if(mapper.addNewriskPerson(id,name)<0){
            return "添加错误";
        }
        return "添加成功";
    }

    @Override
    public String addRiskPerson(int id, int[] persons) {
        if(persons.length == 0){
            return null;
        }
        for (int person : persons) {
            if(mapper.addRiskPerson(id, person) < 0){
                return null;
            }
        }
        return "添加成功";
    }

    @Override
    public String addRiskEquipment(int id, int[] equipments) {
        if(equipments.length == 0){
            return null;
        }
        for (int equipment : equipments) {
            if(mapper.addRiskEquipment(id, equipment) < 0){
                return null;
            }
        }
        return "添加成功";
    }

    @Override
    public String addNewEquipment(Equipment equipment){
        if(equipment == null){
            return null;
        }
        if(mapper.addNewEquipment(equipment)<=0){
            return null;
        }
        return "添加成功";
    }

    @Override
    public String deleteRiskPerson(int id) {
        if(mapper.deletePerson(id) <= 0){
            return null;
        }
        return "删除成功";
    }

    @Override
    public String deleteRiskEquipment(int id) {
        if(mapper.deleteRiskEquipment(id) <= 0){
            return null;
        }
        return "删除成功";
    }

//    @Override
//    public String updateRiskIdentification(Risk riskIdentification) {
//        if(riskIdentification == null){
//            return null;
//        }
//        if(mapper.updateRiskIdentification(riskIdentification) <= 0){
//            return "修改失败";
//        }
//        return "修改成功";
//    }

    @Override
    public int[] selectRiskPerson(int id) {
        return mapper.selectRiskPerson(id);
    }

    @Override
    public int[] selectRiskEquipment(int id) {
        return mapper.selectRiskEquipment(id);
    }

    @Override
    public Risk selectRiskPlan(int id) {
        if(id < 0){
            return null;
        }
        return mapper.selectRiskPlan(id);
    }

    @Override
    public List<Person> searchPersonList(Person person) {
        if(person.getId() < 0){
            return Collections.emptyList();
        }
        return mapper.selectPersonList();
    }

    @Override
    public List<Equipment> searchEquipmentList(Equipment equipment) {
        if(equipment.getId() < 0){
            return Collections.emptyList();
        }
        return mapper.selectEquipmentList();
    }

    // 插入文件记录
    @Override
    public void saveFileRecord(FileUpload fileUpload) {
        mapper.insertFile(fileUpload);
    }

    // 获取所有文件记录
    @Override
    public List<FileUpload> getAllFiles() {
        return mapper.getAllFiles();
    }

    // 根据 ID 获取文件信息
    @Override
    public FileUpload getFileById(int id) {
        return mapper.getFileById(id);
    }

    @Override
    public String createPic(int id,
                             MultipartFile file) {

        String folderPath = "src/main/resources/static/info2/" + id;

        // 创建文件对象
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();  // 创建目录及其父目录
        }

        // 存储文件路径
        String filePaths = null;

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename(); // 获取照片名
            String filePath = folderPath;
            imageUtil.saveImage(file, filePath);  // 保存文件
            filePaths = "/info2/"+ id + "/" + fileName;  // 添加文件路径到列表
        }

        RiskPic riskPic = mapper.containPicById(id);
        if(riskPic == null){
            mapper.createPic(id,filePaths);
        }else{
            mapper.updatePic(id,filePaths);
        }
        return "成功存入";
    }

    @Override
    public String queryPic(int id) {
        RiskPic riskPic = mapper.queryPic(id);
        System.out.println(riskPic);
        String path = null;
        if(riskPic.getFilePath() != null){
            path = riskPic.getFilePath();
        }
        System.out.println(path);
        return path;
    }
}
