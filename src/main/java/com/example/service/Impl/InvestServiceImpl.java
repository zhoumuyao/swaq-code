package com.example.service.Impl;

import com.example.entity.Invest;
import com.example.mapper.InvestMapper;
import com.example.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InvestServiceImpl implements InvestService {
    @Resource
    InvestMapper investMapper;
    @Override
    public String createInvest(Invest invest) {
        if(invest == null){
            return null;
        }
        if (investMapper.hasInvest(invest.getId())){
            investMapper.deleteInvest(invest.getId());
        }
        if(investMapper.createInvest(invest) <= 0){
            return null;
        }
        return "添加成功";
    }

    @Override
    public Invest queryInvest(int id) {
        Invest invest= investMapper.queryInvest(id);
        if (invest== null){
            return null;
        }
        System.out.println(invest);
        return invest;
    }

    @Override
    public String addHandlePerson(int id, int[] persons) {
        if(persons.length == 0){
            return null;
        }
        for (int person : persons) {
            if(investMapper.addHandlePerson(id, person) < 0){
                return null;
            }
        }
        return "添加成功";
    }

    @Override
    public String addHandleEquipment(int id, int[] equipments) {
        if(equipments.length == 0){
            return null;
        }
        for (int equipment : equipments) {
            if(investMapper.addHandleEquipment(id, equipment) < 0){
                return null;
            }
        }
        return "添加成功";
    }

    @Override
    public String deleteHandlePerson(int id) {
        if(investMapper.deleteHandlePerson(id) <= 0){
            return null;
        }
        return "删除成功";
    }

    @Override
    public String deleteHandleEquipment(int id) {
        if(investMapper.deleteHandleEquipment(id) <= 0){
            return null;
        }
        return "删除成功";
    }

    @Override
    public int[] selectHandlePerson(int id) {
        return investMapper.selectHandlePerson(id);
    }

    @Override
    public int[] selectHandleEquipment(int id) {
        return investMapper.selectHandleEquipment(id);
    }
}
