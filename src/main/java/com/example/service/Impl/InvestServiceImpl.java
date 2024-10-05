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
        //mapper.addPerson(riskPlan.getId(), riskPlan.getPerson().e.getId());
        //mapper.addEquipment(riskPlan.getId(), riskPlan.getEquipment().getId());
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
}
