package com.example.mapper;

import com.example.entity.DisposalObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DisposalMapper {
    @Insert("insert into db_disposal(id,objectClass,sampleType,sampleContent,testMethod,result,sampleRequirement) values (#{id},#{objectClass},#{sampleType},#{sampleContent},#{testMethod},#{result},#{sampleRequirement})")
    int addDisposal(DisposalObject disposalObject);

    @Select("select * from db_disposal where id = #{id}")
    List<DisposalObject> searchDisposal(Integer id);
}
