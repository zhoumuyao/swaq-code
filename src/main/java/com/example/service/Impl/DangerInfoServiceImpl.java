package com.example.service.Impl;

import com.example.entity.DangerInfo;
import com.example.entity.vo.DangerVo;
import com.example.mapper.DangerInfoMapper;
import com.example.service.DangerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DangerInfoServiceImpl implements DangerInfoService {
    @Resource
    DangerInfoMapper dangerInfoMapper;
    @Override
    public DangerInfo queryDanger(int caseId) {
        return dangerInfoMapper.queryDangerInfo(caseId);
    }

    @Override
    public String updateDanger(DangerVo dangerVo) {
        if (!dangerInfoMapper.isContains(dangerVo.getCaseId())){
            int res = dangerInfoMapper.addDanger(dangerVo);
            if (res >0)return "插入了一条dangerindo数据";
        }
        if(dangerInfoMapper.updateDangerInfo(dangerVo)>0)
            return "更新了一条dangerinfo数据";
        return "更新失败";
    }
}
