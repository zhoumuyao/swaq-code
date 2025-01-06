package com.example.mapper;

import com.example.entity.DisposalObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DisposalMapper {
    @Insert("insert into db_disposal(id,objectClass,sampleType,sampleContent,testMethod,result,probability,sampleRequirement) values (#{id},#{objectClass},#{sampleType},#{sampleContent},#{testMethod},#{result},#{probability},#{sampleRequirement})")
    @Options(useGeneratedKeys = true, keyProperty = "disposalId")
    int addDisposal(DisposalObject disposalObject);

    @Select("select * from db_disposal where id = #{id}")
    List<DisposalObject> searchDisposal(Integer id);

    @Delete("delete from db_disposal where disposalId = #{disposalId}")
    int deleteDisposal(Integer disposalId);

    @Select("select disposalId from db_disposal where disposalId=#{disposalId} ")
    Integer getdisposalID(DisposalObject disposalObject);

    @Select("select * from db_disposal where disposalId = #{disposalId}")
    List<DisposalObject> searchDisposalID(Integer disposalId);
}
