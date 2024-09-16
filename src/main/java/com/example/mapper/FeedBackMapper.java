package com.example.mapper;

import com.example.entity.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface FeedBackMapper {
    @Select("select  * from  feedback")
    List<Feedback> list();

    @Insert("insert into feedback (feedback, rate, time) values (#{feedback}, #{rate}, #{time})")
    int insert(String feedback, int rate, String time);
}
