package com.example.entity;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class Feedback {
    int id;

    String feedback;

    int rate;

    String time;


}
