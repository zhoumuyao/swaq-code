package com.example.mapper;

import com.example.entity.Equipment;
import com.example.entity.Identify;
import com.example.entity.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IdentifyMapper {
    @Insert("insert into db_identify (id, date, method, result, description, judge) " +
            "values (#{id}, #{date}, #{method}, #{result}, #{description}, #{judge})")
    int createIdentify(Identify identify);

    @Update("update db_identify set method = #{method}, result = #{result}, description = #{description}, judge = #{judge} where id = #{id}")
    int updateIdentify(Identify identify);

    @Insert("insert into db_person_identify_labs (pid, rid) values (#{personId}, #{id})")
    int addLabsPerson(int id, int personId);

    @Insert("insert into db_person_identify_autopsy (pid, rid) values (#{personId}, #{id})")
    int addAutopsyPerson(int id, int personId);

    @Insert("insert into db_person (id, name) values (#{id},#{name})")
    int addNewIdentifyPerson(int id, String name);

    @Delete("delete from db_person_identify_labs where rid = #{id}")
    int deleteLabsPerson(int id);

    @Delete("delete from db_person_identify_autopsy where rid = #{id}")
    int deleteAutopsyPerson(int id);

    @Select("SELECT pid from db_person_identify_labs where rid = #{id}")
    int[] selectLabsPersons(int id);

    @Select("SELECT pid from db_person_identify_autopsy where rid = #{id}")
    int[] selectAutopsyPersons(int id);

    @Select("SELECT * from db_identify where id = #{id}")
    Identify selectIdentify(int id);

    @Select("SELECT * from db_person")
    List<Person> selectPersonList();

    @Select("SELECT * from db_equipment")
    List<Equipment> selectEquipmentList();

    @Select("SELECT eid from db_equipment_identify where rid = #{rid}")
    int[] selectIdentifyEquipment(int id);

    @Insert("insert into db_equipment_identify (eid, rid) values (#{equipmentId}, #{id})")
    int addIdentifyEquipment(int id, int equipment);

    @Delete("delete from db_equipment_risk where rid = #{id}")
    int deleteIdentifyEquipment(int id);
}
