package com.example.service;

import com.example.entity.BiologicalCase;


public interface CaseService {
    int createCase(BiologicalCase biologicalCase);

    BiologicalCase selectCase(int id);
}
