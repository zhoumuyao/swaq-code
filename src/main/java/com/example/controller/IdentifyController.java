package com.example.controller;

import com.example.entity.Equipment;
import com.example.entity.Identify;
import com.example.entity.Person;
import com.example.entity.RestBean;
import com.example.service.IdentifyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/identify")
public class IdentifyController {

    @Resource
    IdentifyService service;

    @PostMapping("/create_idetify")
    public RestBean<String> createIdentify(@RequestParam int id,
                                           @RequestParam String date,
                                           @RequestParam String method,
                                           @RequestParam String result,
                                           @RequestParam String description,
                                           @RequestParam boolean judge,
                                           @RequestParam boolean isUpdate)
    {

//        if(StringUtils.isAnyBlank(date, method, result, description)){
//            return RestBean.failure(400, "结果缺失");
//        }
        Identify idetify = new Identify();
        idetify.setId(id);
        idetify.setDate(LocalDate.parse(date));
        idetify.setResult(result);
        idetify.setMethod(method);
        idetify.setDescription(description);
        idetify.setJudge(judge);
        String s;
        if(isUpdate){
            s = service.updateIdentify(idetify);
            if(s == null){
                return RestBean.failure(400, "更新失败");
            }
        } else{
            s = service.createIdentify(idetify);
            if(s == null){
                return RestBean.failure(400, "添加失败");
            }
        }
        return RestBean.success(s);
    }

    @PostMapping("/add_labsPerson")
    public RestBean<String> addLabsPerson(@RequestParam int id,
                                             @RequestParam(value = "persons[]", required = false) int[] persons){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(persons == null){
            return RestBean.failure(400, "请选中参与警务人员");
        }
        String s = service.addLabsPerson(id, persons);
        return RestBean.success(s);
    }

    @PostMapping("/add_autopsyPerson")
    public RestBean<String> addAutopsyPerson(@RequestParam int id,
                                              @RequestParam(value = "persons[]", required = false) int[] persons){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(persons == null){
            return RestBean.failure(400, "请选中参与警务人员");
        }
        String s = service.addAutopsyPerson(id, persons);
        return RestBean.success(s);
    }

    @PostMapping("/add_newIdentifyPerson")
    public RestBean<String> addNewIdentifyPerson(@RequestParam int id,
                                             @RequestParam String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        String s = service.addNewIdentifyPerson(id,name);
        return RestBean.success(s);

    }

    @PostMapping("/delete_labsPerson")
    public RestBean<String> deleteLabsPerson(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = service.deleteLabsPerson(id);
        return RestBean.success(s);
    }

    @PostMapping("/delete_autopsyPerson")
    public RestBean<String> deleteAutopsyPerson(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = service.deleteAutopsyPerson(id);
        return RestBean.success(s);
    }

    @PostMapping("/select_labsPerson")
    public RestBean<int[]> selectLabsPersons(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] labsPersons = service.selectLabsPersons(id);
        return RestBean.success(labsPersons);
    }

    @PostMapping("/select_autopsyPerson")
    public RestBean<int[]> selectAutopsyPersons(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] autopsyPersons = service.selectAutopsyPersons(id);
        return RestBean.success(autopsyPersons);
    }

    @PostMapping("/select_Identify")
    public RestBean<Identify> searchIdentify(@RequestParam int id){
        Identify identify = service.searchIdentify(id);
        if(identify == null){
            return RestBean.failure(400, null);
        }
        return RestBean.success(identify);
    }

    @PostMapping("/select_person")
    public RestBean<List<Person>> searchPersonList(@RequestParam(required = false, defaultValue = "0") int id,
                                                   @RequestParam(required = false, defaultValue = "") String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        List<Person> personList = service.searchPersonList(person);
        System.out.println("hello");
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

    @PostMapping("/add_identifyEquipment")
    public RestBean<String> addIdentifyEquipment(@RequestParam int id,
                                             @RequestParam(value = "equipments[]", required = false) int[] equipments){
        if(id < 0){
            return RestBean.failure(400, "参数错误");
        }
        if(equipments == null){
            return RestBean.failure(400, "请选中使用设备");
        }
        String s = service.addIdentifyEquipment(id, equipments);
        return RestBean.success(s);
    }

    @PostMapping("/select_identifyEquipment")
    public RestBean<int[]> selectIdentifyEquipment(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, null);
        }
        int[] riskEquipments = service.select_identifyEquipment(id);
        return RestBean.success(riskEquipments);
    }

    @PostMapping("/delete_identifyEquipment")
    public RestBean<String> deleteIdentifyEquipment(@RequestParam int id){
        if (id < 0){
            return RestBean.failure(400, "参数错误");
        }
        String s = service.deleteIdentifyEquipment(id);
        return RestBean.success(s);
    }
}
