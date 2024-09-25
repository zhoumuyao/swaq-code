package com.example.mapper;

import com.example.entity.BiologicalCase;
import com.example.entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReportMapper {
    @Insert("insert into reports (id, year, month, day, hour, minute, second, locations) values (#{id}, #{year}, #{month}, #{day}, #{hour}, #{minute}, #{second}, #{locations})")
    int insert_report(int id, String year, String month, String day, String hour, String minute, String second, String locations);

    @Select("SELECT * from db_case where id = #{id}")
    BiologicalCase select_caseById(int id);

    @Select("SELECT * from reports where id = #{id}")
    Report select_reportById(int id);
}
