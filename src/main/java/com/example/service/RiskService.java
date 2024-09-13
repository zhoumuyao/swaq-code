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
}
