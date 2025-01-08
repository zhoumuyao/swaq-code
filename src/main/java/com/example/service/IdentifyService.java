package com.example.service;

import com.example.entity.Identify;
import com.example.entity.Person;
import com.example.entity.Risk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IdentifyService {
    String createIdentify(Identify identify);

    String updateIdentify(Identify identify);

    String addIdentifyPerson(int id, int[] persons);

    String addNewIdentifyPerson(int id, String name);

    String deleteIdentifyPerson(int id);

    int[] selectIdentifyPersons(int id);

    Identify searchIdentify(int id);

    List<Person> searchPersonList(Person person);
}
