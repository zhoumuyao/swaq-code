package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RiskMapper {
    @Insert("insert into db_risk (id, date, time, longitude, latitude, country, province, urban, description, type, method) " +
            "values (#{id}, #{date}, #{time}, #{longitude}, #{latitude}, #{country}, #{province}, #{urban}, #{description}, #{type}, #{method})")
    int createPlan(RiskPlan riskPlan);

    @Update("update db_risk set date = #{date}, time = #{time}, longitude = #{longitude}, latitude = #{latitude}, country = #{country}, province = #{province}, "
            + "urban = #{urban}, description = #{description}, type = #{type}, method = #{method} where id = #{id}")
    int updatePlan(RiskPlan riskPlan);

    @Update("update db_risk set object_class = #{objectClass}, sample_type = #{sampleType}, sample_content = #{sampleContent}, test_method = #{testMethod}, "+
            "sample_requirements = #{sampleRequirement} where id = #{id}")
    int updateRiskIdentification(RiskIdentification riskIdentification);

    @Update("update db_risk set person_id = #{personId} where id = #{id}")
    int addPerson(int id, int personId);

    @Update("update db_risk set equipment_id = #{equipmentId} where id = #{id}")
    int addEquipment(int id, int equipmentId);

    @Select("SELECT * from db_risk where id = #{id}")
    RiskPlan selectRiskPlan(int id);


    @Select("SELECT * from db_person")
    List<Person> selectPersonList();

    @Select("SELECT * from db_equipment")
    List<Equipment> selectEquipmentList();
}
