package com.example.entity.vo;

import com.alibaba.fastjson2.JSON;
//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class InvestVo {
    int id;

    String date;

    String time;

    String airQuality;

    String water;

    String soil;

    String personelDensity;

    int temperature;
    //    湿度
    int humi;

    String weather;

    String windSpeed;

    String windDirection;
    public Object getPersonelDensity() {
        return JSON.parse(this.personelDensity);
    }

    public void setPersonelDensity(Object personelDensityObject) {
        this.personelDensity = JSON.toJSONString(personelDensityObject);
    }


    String gather;
    //    @Setter
    public void setGather(Object gatherObject) {
        this.gather = JSON.toJSONString(gatherObject); // 将对象序列化为 JSON 字符串
    }
    //    @Getter
    public Object getGather() {
        return JSON.parse(this.gather); // 将 JSON 字符串反序列化为 Java 对象
    }



}
