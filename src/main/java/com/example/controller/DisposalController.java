package com.example.controller;

import com.example.entity.DisposalObject;
import com.example.entity.RestBean;
import com.example.service.DisposalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.relational.core.sql.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Validated
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
                                        @RequestParam String sampleRequirement){
        if(StringUtils.isAnyBlank(objectClass,sampleType,result,testMethod, sampleContent) || id<0 ){
            return RestBean.failure(400,"请完成填写所有参数");
        }
        DisposalObject disposalObject = new DisposalObject();
//        disposalObject.setDisposalId(disposalId);
        disposalObject.setId(id);
        disposalObject.setObjectClass(objectClass);
        disposalObject.setSampleType(sampleType);
        disposalObject.setSampleContent(sampleContent);
        disposalObject.setTestMethod(testMethod);
        disposalObject.setResult(result);
        disposalObject.setSampleRequirement(sampleRequirement);
        String s = disposalService.addDisposal(disposalObject);
        if(s == null){
            return RestBean.failure(400,"参数错误");
        }
        return RestBean.success(s);
    }

    @PostMapping("/search_disposal")
    public RestBean<List<DisposalObject>> searchDisposal(@RequestParam Integer id){
        List<DisposalObject> disposalObjectList = disposalService.searchDisposal(id);
        return RestBean.success(disposalObjectList);
    }
}
