package com.example.controller;


import com.example.entity.*;
import com.example.service.RiskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private static final String UPLOAD_DIR = "D:/uploads/";

    @Resource
    RiskService service;

    @PostMapping("/create_plan")
    public RestBean<String> createPlan (@RequestParam int id,
                                        @RequestParam String date,
                                        @RequestParam String time,
                                        @RequestParam String longitude,
                                        @RequestParam String latitude,
                                        @RequestParam String country,
                                        @RequestParam String province,
                                        @RequestParam String urban,
                                        @RequestParam String description,
                                        @RequestParam String type,
                                        @RequestParam boolean isUpdate,
                                        @RequestParam(value = "objectDescription", required = false) String objectDescription){
        if(StringUtils.isAnyBlank(date, time, latitude, longitude, country, province, urban, description) || type.isEmpty() || id < 0 ) {
            return RestBean.failure(400, "请完成填写所有参数");
        }
        Risk riskPlan = new Risk();
        riskPlan.setId(id);
        riskPlan.setDate(LocalDate.parse(date));
        riskPlan.setTime(LocalTime.parse(time));
        riskPlan.setLongitude(Double.parseDouble(longitude));
        riskPlan.setLatitude(Double.parseDouble(latitude));
        riskPlan.setCountry(country);
        riskPlan.setProvince(province);
        riskPlan.setUrban(urban);
        riskPlan.setDescription(description);
        riskPlan.setType(type);
        riskPlan.setObjectDescription(objectDescription);
        String s;
        if(isUpdate){
            s = service.updatePlan(riskPlan);
            if(s == null){
                return RestBean.failure(400, "更新失败");
            }
        } else{
            s = service.createPlan(riskPlan);
            if(s == null){
                return RestBean.failure(400, "添加失败");
            }
        }
        return RestBean.success(s);

    }

//    @PostMapping("/update_riskIdentification")
//    public RestBean<String> updateRiskIdentification (@RequestParam int id,
//                                                      @RequestParam int objectClass,
//                                                      @RequestParam int sampleType,
//                                                      @RequestParam String sampleContent,
//                                                      @RequestParam int testMethod,
//                                                      @RequestParam String sampleRequirement
//    ){
//        if(StringUtils.isAnyBlank(sampleRequirement, sampleContent) || testMethod < 0 || sampleType < 0 || objectClass < 0) {
//            return RestBean.failure(400, "请完成填写所有参数");
//        }
//        Risk riskIdentification = new Risk();
//        riskIdentification.setId(id);
//        riskIdentification.setSampleType(sampleType);
//        riskIdentification.setSampleContent(sampleContent);
//        riskIdentification.setTestMethod(testMethod);
//        riskIdentification.setSampleRequirement(sampleRequirement);
//        riskIdentification.setObjectClass(objectClass);
//        String s = service.updateRiskIdentification(riskIdentification);
//        if(s == null){
//            return RestBean.failure(400, "参数错误");
//        }
//        else{
//            return RestBean.success(s);
//        }
//    }

//    @PostMapping("/show_somePlan")
//    public RestBean<BiologicalCase> showSomePlan(@RequestParam int id){
//        BiologicalCase
//    }

    @PostMapping("/add_riskPerson")
    public RestBean<String> addRiskPerson( @RequestParam int id,
                                          @RequestParam(value = "persons[]", required = false) int[] persons){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(persons == null){
            return RestBean.failure(400, "请选中参与警务人员");
        }
        String s = service.addRiskPerson(id, persons);
        return RestBean.success(s);
    }

    @PostMapping("/add_newriskPerson")
    public RestBean<String> addNewriskPerson(@RequestParam int id,
                                             @RequestParam String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        String s = service.addNewriskPerson(id,name);
        return RestBean.success(s);

    }

    @PostMapping("/add_riskEquipment")
    public RestBean<String> addRiskEquipment(@RequestParam int id,
                                          @RequestParam(value = "equipments[]", required = false) int[] equipments){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(equipments == null){
            return RestBean.failure(400, "请选中使用设备");
        }
        String s = service.addRiskEquipment(id, equipments);
        return RestBean.success(s);
    }

    @PostMapping("/add_newEquipment")
    public RestBean<String> addNewEquipment(@RequestParam int id,
                                            @RequestParam String name,
                                            @RequestParam int type1,
                                            @RequestParam int type2,
                                            @RequestParam int type3,
                                            @RequestParam int type4,
                                            @RequestParam int type5,
                                            @RequestParam int type6,
                                            @RequestParam int type7){
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName(name);
        equipment.setType1(type1);
        equipment.setType2(type2);
        equipment.setType3(type3);
        equipment.setType4(type4);
        equipment.setType5(type5);
        equipment.setType6(type6);
        equipment.setType7(type7);
        String s = service.addNewEquipment(equipment);
        return RestBean.success(s);
    }

    @PostMapping("/delete_riskPerson")
    public RestBean<String> deleteRiskPerson(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = service.deleteRiskPerson(id);
        return RestBean.success(s);
    }

    @PostMapping("/delete_riskEquipment")
    public RestBean<String> deleteRiskEquipment(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = service.deleteRiskEquipment(id);
        return RestBean.success(s);
    }

    @PostMapping("/select_RiskPerson")
    public RestBean<int[]> selectRiskPersons(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] riskPersons = service.selectRiskPerson(id);
        return RestBean.success(riskPersons);
    }

    @PostMapping("/select_RiskEquipment")
    public RestBean<int[]> selectRiskEquipment(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] riskEquipments = service.selectRiskEquipment(id);
        return RestBean.success(riskEquipments);
    }

    @PostMapping("/select_riskPlan")
    public RestBean<Risk> searchRiskPlan(@RequestParam int id){
        Risk riskPlan = service.selectRiskPlan(id);
        if(riskPlan == null){
            return RestBean.failure(400, null);
        }
        return RestBean.success(riskPlan);
    }

    @PostMapping("/select_person")
    public RestBean<List<Person>> searchPersonList(@RequestParam(required = false, defaultValue = "0") int id,
                                                @RequestParam(required = false, defaultValue = "") String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        List<Person> personList = service.searchPersonList(person);
        return RestBean.success(personList);
    }

    @PostMapping("/select_equipment")
    public RestBean<List<Equipment>> searchEquipmentList(@RequestParam(required = false, defaultValue = "0") int id,
                                                 @RequestParam(required = false, defaultValue = "") String name){
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName(name);
        List<Equipment> equipmentList = service.searchEquipmentList(equipment);
        return RestBean.success(equipmentList);
    }

    @PostMapping("/upload")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        if (file.isEmpty()) {
            result.put("message", "文件为空");
            return result;
        }

        try {
            // 保存文件到本地目录
            String fileName = file.getOriginalFilename();
            File dest = new File(UPLOAD_DIR + fileName);
            file.transferTo(dest);

            // 构造文件 URL（实际应用中应该指向静态资源服务器）
            String fileUrl = "http://localhost:8080/images/" + fileName;

            // 将文件信息保存到数据库
            FileUpload fileRecord = new FileUpload();
            fileRecord.setFileName(fileName);
            fileRecord.setFileUrl(fileUrl);

            service.saveFileRecord(fileRecord);

            // 返回上传结果
            result.put("url", fileUrl);
            result.put("message", "文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            result.put("message", "文件上传失败");
        }
        return result;
    }

    // 获取所有上传的文件记录
    @GetMapping
    public Map<String, Object> getAllFiles() {
        Map<String, Object> result = new HashMap<>();
        result.put("files", service.getAllFiles());
        return result;
    }

    @PostMapping("/uploads")
    public RestBean<String> upLoadPicture(@RequestParam(value = "file",required = false)MultipartFile file) {
        // 判断文件是否为空
        if(file.isEmpty()){
            return RestBean.failure(400,"文件为空");
        }
        // 获取传过来的文件名字
        String OriginalFilename=file.getOriginalFilename();
        // 为了防止重名覆盖，获取系统时间戳+原始文件的后缀名
        String fileName=System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        // 设置保存地址（这里是转义字符）
        //1.后台保存位置
        String path = "D:\\biology_security\\swaq-web-web-dlx-46\\public\\image\\";
        File dest=new File(path+fileName);
        // 判断文件是否存在
        if(!dest.getParentFile().exists()){
            // 不存在就创建一个
            dest.getParentFile().mkdirs();
        }
        try {
            // 后台上传
            file.transferTo(dest);
            return RestBean.success(fileName);
        }catch (Exception e){
            e.printStackTrace();
            return RestBean.failure(400,"上传失败");
        }

    }

}
