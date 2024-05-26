package com.example.service;

import com.example.entity.*;

import java.util.List;

public interface RiskService {
    String createPlan(RiskPlan riskPlan);

    String updatePlan(RiskPlan riskPlan);

    String updateRiskIdentification(RiskIdentification riskIdentification);

    RiskPlan selectRiskPlan(int id);

    List<Person> searchPersonList(Person person);

    List<Equipment> searchEquipmentList(Equipment equipment);
}
