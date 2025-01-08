package com.example.mapper;

import com.example.entity.Identify;
import com.example.entity.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IdentifyMapper {
    @Insert("insert into db_identify (id, date, method, result, description, judge) " +
            "values (#{id}, #{date}, #{method}, #{result}, #{description}, #{judge})")
    int createIdentify(Identify identify);

    @Update("update db_identify set date = #{date}, method = #{method}, result = #{result}, description = #{description}, judge = #{judge} where id = #{id}")
    int updateIdentify(Identify identify);

    @Insert("insert into db_person_identify (pid, rid) values (#{personId}, #{id})")
    int addIdentifyPerson(int id, int personId);

    @Insert("insert into db_person (id, name) values (#{id},#{name})")
    int addNewIdentifyPerson(int id, String name);

    @Delete("delete from db_person_identify where rid = #{id}")
    int deletePerson(int id);

    @Select("SELECT pid from db_person_identify where rid = #{id}")
    int[] selectIdentifyPersons(int id);

    @Select("SELECT * from db_identify where id = #{id}")
    Identify selectIdentify(int id);

    @Select("SELECT * from db_person")
    List<Person> selectPersonList();
}
