package com.example.mapper;

import com.example.entity.BiologyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BiologyInfoMapper {

    @Select("select * from biologyInfo where dangerName = #{dangerName}")
    BiologyInfo searchInfo(String dangerName);
}
