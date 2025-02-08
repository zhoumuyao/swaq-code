package com.example.service;

import com.example.entity.Equipment;
import com.example.entity.Identify;
import com.example.entity.Person;
import com.example.entity.Risk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IdentifyService {
    String createIdentify(Identify identify);

    String updateIdentify(Identify identify);


    String addLabsPerson(int id, int[] persons);

    String addAutopsyPerson(int id, int[] persons);

    String addNewIdentifyPerson(int id, String name);

    String deleteLabsPerson(int id);

    String deleteAutopsyPerson(int id);


    int[] selectLabsPersons(int id);

    int[] selectAutopsyPersons(int id);

    Identify searchIdentify(int id);

    List<Person> searchPersonList(Person person);

    List<Equipment> searchEquipmentList(Equipment equipment);

    int[] select_identifyEquipment(int id);

    String addIdentifyEquipment(int id, int[] equipments);

    String deleteIdentifyEquipment(int id);
}
