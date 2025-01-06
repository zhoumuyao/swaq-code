package com.example.service.Impl;

import com.example.entity.BiologyInfo;
import com.example.mapper.BiologyInfoMapper;
import com.example.service.BiologyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BiologyInfoServiceImpl implements BiologyInfoService {

    @Resource
    BiologyInfoMapper mapper;

    @Override
    public BiologyInfo searchInfo(String dangerName) {
        BiologyInfo info;
        info = mapper.searchInfo(dangerName);
        return info;
    }
}
