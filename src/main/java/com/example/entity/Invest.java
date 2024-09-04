package com.example.entity;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Invest {
    int id;

    Date date;

    Time time;

    String air_quality;

    String water;

    String soil;

    String personel_density;

    String distribution;

    String gather;

    String transportation;

    int temperature;
//    湿度
    int humi;

    String weather;

    String wind_speed;

    String wind_direction;

}
