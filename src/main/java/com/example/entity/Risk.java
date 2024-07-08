package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Risk {

    int id;
    LocalDate date;
    LocalTime time;
    double longitude;
    double latitude;
    String country;
    String province;
    String urban;
    String description;
    int type;
    int objectClass;
    int sampleType;
    String sampleContent;
    int testMethod;
    String sampleRequirement;
}
