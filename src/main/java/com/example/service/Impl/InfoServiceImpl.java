package com.example.service.Impl;

import com.example.mapper.InfoMapper;
import com.example.service.InfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InfoServiceImpl implements InfoService {

    @Resource
    InfoMapper infoMapper;
    @Override
    public String createInfo(int id, String name) {
        infoMapper.createInfo(id,name);
        return "成功存入";
    }
}
