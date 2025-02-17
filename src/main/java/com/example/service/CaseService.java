package com.example.service;

import com.example.entity.BiologicalCase;

import java.util.List;


public interface CaseService {
    int createCase(BiologicalCase biologicalCase);
    Boolean deleteCase(int id);
    BiologicalCase selectCase(int id);

    //查询全部案例数据
    List<BiologicalCase> searchAllCase();
}
