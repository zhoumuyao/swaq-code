package com.example.service.Impl;

import com.example.entity.*;
import com.example.mapper.RiskMapper;
import com.example.service.RiskService;
import java.util.Collections;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


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
}
