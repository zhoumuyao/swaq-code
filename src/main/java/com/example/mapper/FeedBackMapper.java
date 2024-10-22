package com.example.mapper;

import com.example.entity.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedBackMapper {
    @Select("select  * from  feedback")
    List<Feedback> list();

    @Insert("INSERT INTO feedback (id, feedback, rate, time) VALUES (#{id}, #{feedback}, #{rate}, #{time}) " +
            "ON DUPLICATE KEY UPDATE feedback = #{feedback}, rate = #{rate}, time = #{time}")
    int insertOrUpdate(int id, String feedback, int rate, String time);

    @Select("select  * from feedback where id = #{id}")
    Feedback select(int id);
}
