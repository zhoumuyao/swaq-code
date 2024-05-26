package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BiologicalCase {

    int id;
    LocalDate date;
    LocalTime time;
    int longitude;
    int latitude;
    String country;
    String province;
    String urban;
    String description;

    public BiologicalCase() {
    }

}
