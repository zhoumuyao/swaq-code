package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BiologicalCase {

    int id;
    LocalDate date;
    LocalTime time;
    double longitude;
    double latitude;
    String country;
    String province;
    String urban;
    String description;
    int casualties;
    String symptomMessage;
    double influenceScope;


    public BiologicalCase() {
    }

}
