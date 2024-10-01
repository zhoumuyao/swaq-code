package com.example.mapper;

import com.example.entity.BiologicalCase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CaseMapper {

    @Insert("insert into db_case (date, time, longitude, latitude, country, province, urban, description, casualties, symptom_message, influence_scope) " +
            "values (#{date}, #{time}, #{longitude}, #{latitude}, #{country}, #{province}, #{urban}, #{description}, #{casualties}, #{symptomMessage}, #{influenceScope})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCase(BiologicalCase biologicalCase);

    @Select("SELECT * from db_case where id = #{id}")
    BiologicalCase selectCase(int id);

    //查询全部部门数据
    @Select("select * from db_case")
    List<BiologicalCase> searchAllCase();
}
