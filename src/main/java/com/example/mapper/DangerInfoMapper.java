package com.example.mapper;

import com.example.entity.DangerInfo;
import com.example.entity.Invest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DangerInfoMapper {
    @Select("SELECT * FROM db_danger_info WHERE id = #{id}")
    DangerInfo queryDangerInfo(@Param("id") int id);
}
