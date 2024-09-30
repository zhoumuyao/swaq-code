package com.example.entity;
import lombok.Data;

@Data
public class Report {
    int id;
    String year;
    String month;
    String day;
    String hour;
    String minute;
    String second;
    String locations;

}
