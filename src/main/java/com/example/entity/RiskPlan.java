package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class RiskPlan {

    int id;
    LocalDate date;
    LocalTime time;
    int longitude;
    int latitude;
    String country;
    String province;
    String urban;
    String description;
    int type;
    int method;
}
