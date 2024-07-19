package com.example.service.Impl;

import com.example.entity.DisposalObject;
import com.example.mapper.DisposalMapper;
import com.example.service.DisposalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DisposalServiceImpl implements DisposalService {
    @Resource
    DisposalMapper mapper;
    @Override
    public String addDisposal(DisposalObject disposalObject){
        if(disposalObject == null){
            return null;
        }
        if(mapper.addDisposal(disposalObject)<=0){
            return null;
        }
        return "添加成功";
    }

    @Override
    public List<DisposalObject> searchDisposal(Integer id) {
        return mapper.searchDisposal(id);
    }
}
