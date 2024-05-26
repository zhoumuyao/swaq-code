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
    public String createPlan(RiskPlan riskPlan) {
        if(riskPlan == null){
            return null;
        }
        if(mapper.createPlan(riskPlan) <= 0){
            return "添加失败";
        }
        //mapper.addPerson(riskPlan.getId(), riskPlan.getPerson().e.getId());
        //mapper.addEquipment(riskPlan.getId(), riskPlan.getEquipment().getId());
        return "添加成功";
    }

    @Override
    public String updatePlan(RiskPlan riskPlan) {
        if(riskPlan == null){
            return null;
        }
        if(mapper.updatePlan(riskPlan) <= 0){
            return "修改失败";
        }
        return "修改成功";
    }

    @Override
    public String updateRiskIdentification(RiskIdentification riskIdentification) {
        if(riskIdentification == null){
            return null;
        }
        if(mapper.updateRiskIdentification(riskIdentification) <= 0){
            return "修改失败";
        }
        return "修改成功";
    }

    @Override
    public RiskPlan selectRiskPlan(int id) {
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
