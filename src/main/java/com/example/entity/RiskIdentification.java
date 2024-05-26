package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class RiskIdentification {
    int id;
    int objectClass;
    int sampleType;
    String sampleContent;
    int testMethod;
    String sampleRequirement;
}
