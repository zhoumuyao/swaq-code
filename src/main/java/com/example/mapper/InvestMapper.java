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
}
