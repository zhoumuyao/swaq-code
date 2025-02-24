package com.example.controller;

import com.example.entity.DisposalObject;
import com.example.entity.RestBean;
import com.example.service.DisposalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.relational.core.sql.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Validated //开始参数校验功能
@RestController
@RequestMapping("/api/disposal")
public class DisposalController {
    @Resource
    DisposalService disposalService;

    @PostMapping("/add_disposal")
    public RestBean<String> addDisposal(
                                        @RequestParam Integer id,
                                        @RequestParam String objectClass,
                                        @RequestParam String sampleType,
                                        @RequestParam String sampleContent,
                                        @RequestParam String testMethod,
                                        @RequestParam String result,
                                        @RequestParam String probability,
                                        @RequestParam String sampleRequirement){
        if(StringUtils.isAnyBlank(objectClass,sampleType,result,testMethod, sampleContent) || id<0 ){
            return RestBean.failure(400,"请完整填写参数");
        }
        DisposalObject disposalObject = new DisposalObject();
//        disposalObject.setDisposalId(disposalId);
        disposalObject.setId(id);
        disposalObject.setObjectClass(objectClass);
        disposalObject.setSampleType(sampleType);
        disposalObject.setSampleContent(sampleContent);
        disposalObject.setTestMethod(testMethod);
        disposalObject.setResult(result);
        disposalObject.setProbability(probability);
        disposalObject.setSampleRequirement(sampleRequirement);

        Integer disposalID = disposalService.addDisposal(disposalObject);
        if(disposalID == null){
            return RestBean.failure(404,"添加失败");
        }
        return RestBean.success("添加成功");
    }

    @PostMapping("/search_disposal")
    public RestBean<List<DisposalObject>> searchDisposal(@RequestParam Integer id){
        List<DisposalObject> disposalObjectList = disposalService.searchDisposal(id);
        return RestBean.success(disposalObjectList);
    }

    @PostMapping("/delete_disposal")
    public RestBean<String> deleteDisposal(@RequestParam Integer disposalId){
        String str = disposalService.deleteDisposal(disposalId);
        if(Objects.equals(str, "成功删除")){
            return RestBean.success(str);
        }else {
            return RestBean.failure(400,"删除失败");
        }
    }
}
