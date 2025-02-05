package com.example.entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Identify {
    int id;
    LocalDate date;
    String method;
    String result;
    String description;
    String baseSequence;
    boolean judge;
}
