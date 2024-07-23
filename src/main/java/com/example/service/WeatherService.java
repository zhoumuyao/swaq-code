package com.example.service;

import com.example.entity.Weather;
import org.springframework.stereotype.Service;


import org.springframework.web.util.UriUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WeatherService {

    public static void getDistrictId(String adcode){
        Weather snCal = new Weather();

    }
    /**
     * 默认ak
     * 选择了ak，使用IP白名单校验：
     * 根据您选择的AK已为您生成调用代码
     * 检测到您当前的ak设置了IP白名单校验
     * 您的IP白名单中的IP非公网IP，请设置为公网IP，否则将请求失败
     * 请在IP地址为0.0.0.0/0 外网IP的计算发起请求，否则将请求失败
     */
    public static String requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return "";
        }

        StringBuilder queryString = new StringBuilder();
        queryString.append(strUrl);
        for (Map.Entry<?, ?> pair : param.entrySet()) {
            queryString.append(pair.getKey()).append("=");
            //    第一种方式使用的 jdk 自带的转码方式  第二种方式使用的 spring 的转码方法 两种均可
            //    queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8").replace("+", "%20") + "&");
            queryString.append(UriUtils.encode((String) pair.getValue(), "UTF-8")).append("&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        java.net.URL url = new URL(queryString.toString());
        // 打印API地址
        // System.out.println(queryString.toString());
        URLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        isr.close();
        // 打印获取到的信息
        // System.out.println("AK: " + buffer.toString());
        return buffer.toString();


    }

    public String getWeather(String districtId) throws Exception {

        Weather snCal = new Weather();
        String URL = snCal.getURL();
        String AK = snCal.getAK();
//        String districtId = snCal.getDistrictId();
        String dataType = "all";

        Map<String, String> params = new LinkedHashMap<>();
        params.put("district_id", districtId);
        params.put("data_type", dataType);
        params.put("ak", AK);

       return requestGetAK(URL, params);

    }
}


