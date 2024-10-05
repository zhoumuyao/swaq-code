package com.example.service;

import com.example.entity.Invest;

public interface InvestService {
    String createInvest(Invest invest);

    Invest queryInvest(int id);

    String addHandlePerson(int id, int[] persons);

    String addHandleEquipment(int id, int[] equipments);
    String deleteHandlePerson(int id);

    String deleteHandleEquipment(int id);

    int[] selectHandlePerson(int id);

    int[] selectHandleEquipment(int id);
}
