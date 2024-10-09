package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RiskMapper {
    @Insert("insert into db_risk (id, date, time, longitude, latitude, country, province, urban, description, type, objectDescription) " +
            "values (#{id}, #{date}, #{time}, #{longitude}, #{latitude}, #{country}, #{province}, #{urban}, #{description}, #{type}, #{objectDescription})")
    int createPlan(Risk riskPlan);

    @Update("update db_risk set date = #{date}, time = #{time}, longitude = #{longitude}, latitude = #{latitude}, country = #{country}, province = #{province}, "
            + "urban = #{urban}, description = #{description}, type = #{type}, objectDescription=#{objectDescription} where id = #{id}")
    int updatePlan(Risk riskPlan);

//    @Update("update db_risk set object_class = #{objectClass}, sample_type = #{sampleType}, sample_content = #{sampleContent}, test_method = #{testMethod}, "+
//            "sample_requirement = #{sampleRequirement} where id = #{id}")
//    int updateRiskIdentification(Risk riskIdentification);

    @Insert("insert into db_person (id, name) values (#{id},#{name})")
    int addNewriskPerson(int id,String name);

    @Insert("insert into db_person_risk (pid, rid) values (#{personId}, #{id})")
    int addRiskPerson(int id, int personId);

    @Insert("insert into db_equipment (id, name, type1, type2, type3, type4, type5, type6, type7) values (#{id},#{name},#{type1},#{type2},#{type3},#{type4},#{type5},#{type6},#{type7})")
    int addNewEquipment(Equipment equipment);

    @Insert("insert into db_equipment_risk (eid, rid) values (#{equipmentId}, #{id})")
    int addRiskEquipment(int id, int equipmentId);

    @Delete("delete from db_person_risk where rid = #{id}")
    int deletePerson(int id);

    @Delete("delete from db_equipment_risk where rid = #{id}")
    int deleteRiskEquipment(int id);

    @Select("SELECT * from db_risk where id = #{id}")
    Risk selectRiskPlan(int id);

    @Select("SELECT pid from db_person_risk where rid = #{rid}")
    int[] selectRiskPerson(int rid);

    @Select("SELECT eid from db_equipment_risk where rid = #{rid}")
    int[] selectRiskEquipment(int rid);

    @Select("SELECT * from db_person")
    List<Person> selectPersonList();

    @Select("SELECT * from db_equipment")
    List<Equipment> selectEquipmentList();

    @Insert("insert into db_fileUpload (fileName,fileUrl) values (#{fileName},#{fileUrl})")
    void insertFile(FileUpload fileUpload);

    // 查询所有文件记录
    @Select("SELECT * FROM db_fileUpload")
    List<FileUpload> getAllFiles();

    // 根据 ID 查询文件信息
    @Select("SELECT * FROM db_fileUpload WHERE id = #{id}")
    FileUpload getFileById(int id);

    //插入图片路径
    @Insert({
            "<script>",
            "INSERT INTO db_riskPic (id",
            "<if test='filePath != null'>, filePath</if>",
            ") VALUES (#{id}",
            "<if test='filePath != null'>, #{filePath}</if>",
            ")",
            "</script>"
    })
    int createPic(
            int id,
            String filePath
    );

    //根据id查询图片信息
    @Select("SELECT * FROM db_riskPic  WHERE id = #{id}")
    RiskPic containPicById(int id);

    @Select("SELECT * from db_riskPic where id= #{id}")
    RiskPic queryPic(int id);

    @Update({
            "<script>",
            "UPDATE db_riskPic",
            "<set>",
            "<if test='filePath != null and filePath != \"\"'>filePath = #{filePath},</if>",
            "</set>",
            "WHERE id = ${id}",
            "</script>"
    })
    void updatePic(int id,
                    String filePath);
}

