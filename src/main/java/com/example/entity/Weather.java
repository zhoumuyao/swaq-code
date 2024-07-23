package com.example.entity;

import lombok.Data;


@Data
public class Weather {

    private String URL = "https://api.map.baidu.com/weather/v1/?";

    private String AK = "Vbx7aGwWX9GeAFpc9XFd4lOCAk26XY2X";

    private String districtId = "320100";
}