package com.example.service;

import com.example.entity.*;

import java.util.List;

public interface RiskService {
    String createPlan(Risk riskPlan);

    String updatePlan(Risk riskPlan);

    String addNewriskPerson(int id,String name);

    String addRiskPerson(int id, int[] persons);

    String addRiskEquipment(int id, int[] equipments);

    String addNewEquipment(Equipment equipment);

    String deleteRiskPerson(int id);

    String deleteRiskEquipment(int id);

//    String updateRiskIdentification(Risk riskIdentification);

    int[] selectRiskPerson(int id);

    int[] selectRiskEquipment(int id);

    Risk selectRiskPlan(int id);

    List<Person> searchPersonList(Person person);

    List<Equipment> searchEquipmentList(Equipment equipment);

    // 保存文件记录到数据库
    void saveFileRecord(FileUpload fileUpload);

    // 获取所有文件记录
    List<FileUpload> getAllFiles();

    // 根据 ID 获取文件信息
    FileUpload getFileById(int id);
}
