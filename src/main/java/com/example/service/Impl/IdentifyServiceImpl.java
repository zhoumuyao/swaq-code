package com.example.service.Impl;

import com.example.entity.Identify;
import com.example.entity.Person;
import com.example.entity.Risk;
import com.example.mapper.IdentifyMapper;
import com.example.service.IdentifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class IdentifyServiceImpl implements IdentifyService {

    @Resource
    IdentifyMapper mapper;

    @Override
    public String createIdentify(Identify idetify){
        if(idetify == null){
            return null;
        }
        if(mapper.createIdentify(idetify) <= 0){
            return null;
        }
        return "添加成功";
    }

    @Override
    public String updateIdentify(Identify identify){
        if(identify == null){
            return null;
        }
        if(mapper.updateIdentify(identify) <= 0){
            return null;
        }
        return "修改成功";
    }
    @Override
    public String addIdentifyPerson(int id, int[] persons){
        if(persons.length == 0){
            return null;
        }
        for (int person : persons) {
            if(mapper.addIdentifyPerson(id, person) < 0){
                return null;
            }
        }
        return "添加成功";
    }
    @Override
    public String addNewIdentifyPerson(int id, String name){
        if(mapper.addNewIdentifyPerson(id,name)<0){
            return "添加错误";
        }
        return "添加成功";
    }

    @Override
    public String deleteIdentifyPerson(int id){
        if(mapper.deletePerson(id) <= 0){
            return null;
        }
        return "删除成功";
    }

    @Override
    public int[] selectIdentifyPersons(int id){
        return mapper.selectIdentifyPersons(id);
    }

    @Override
    public Identify searchIdentify(int id){
        if(id < 0){
            return null;
        }
        return mapper.selectIdentify(id);
    }

    @Override
    public List<Person> searchPersonList(Person person){
        if(person.getId() < 0){
            return Collections.emptyList();
        }
        return mapper.selectPersonList();
    }
}
