package com.example.mapper;

import com.example.entity.Risk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoMapper {

    @Insert("insert into db_info_input(id,name) values (#{id}, #{name})")
    int createInfo(int id,String name);
}
