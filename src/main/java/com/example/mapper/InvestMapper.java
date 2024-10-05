package com.example.mapper;

import com.example.entity.Invest;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface InvestMapper {
    @Insert("insert into db_invest(id,date,time,air_quality,water,soil,personel_density,gather,temperature,weather,wind_speed,wind_direction,humi)"+
    "values (#{id},#{date},#{time},#{airQuality},#{water},#{soil},#{personelDensity},#{gather},#{temperature},#{weather},#{windSpeed},#{windDirection},#{humi})")
    int createInvest(Invest invest);

    @Select("SELECT * FROM db_invest WHERE id = #{id}")
    Invest queryInvest(@Param("id") int id);

    @Select("SELECT COUNT(*) > 0 FROM db_invest WHERE id = #{id}")
    boolean hasInvest(int id);

    @Delete("DELETE FROM db_invest WHERE id = ${id}")
    void deleteInvest(int id);

    @Insert("insert into db_person_handle (pid, hid) values (#{personId}, #{id})")
    int addHandlePerson(int id, int personId);

    @Insert("insert into db_equipment_handle (eid, hid) values (#{equipmentId}, #{id})")
    int addHandleEquipment(int id, int equipmentId);

    @Delete("delete from db_person_handle where hid = #{id}")
    int deletePerson(int id);

    @Delete("delete from db_equipment_handle where hid = #{id}")
    int deleteHandleEquipment(int id);

    @Select("SELECT pid from db_person_handle where hid = #{hid}")
    int[] selectHandlePerson(int rid);

    @Select("SELECT eid from db_equipment_handle where hid = #{hid}")
    int[] selectHandleEquipment(int rid);
}
