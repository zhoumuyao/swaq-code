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
    public Integer addDisposal(DisposalObject disposalObject){
        if(disposalObject == null){
            return null;
        }
        if(mapper.addDisposal(disposalObject)<=0){
            return null;
        }
        System.out.println(mapper.getdisposalID(disposalObject));
        return mapper.getdisposalID(disposalObject);
    }

    @Override
    public List<DisposalObject> searchDisposal(Integer id) {
        return mapper.searchDisposal(id);
    }

    @Override
    public String deleteDisposal(Integer disposalId) {
        if(mapper.searchDisposalID(disposalId)!=null){
            int deletecol = mapper.deleteDisposal(disposalId);
            if(deletecol>0){
                return "成功删除";
            }else {
                return "删除失败";
            }
        }
        return "没有该处置对象";
    }
}
