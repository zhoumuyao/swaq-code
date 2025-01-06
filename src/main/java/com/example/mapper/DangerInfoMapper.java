package com.example.mapper;

import com.example.entity.DangerInfo;
import com.example.entity.Invest;
import com.example.entity.vo.DangerVo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DangerInfoMapper {
    @Select("SELECT * FROM db_danger_info WHERE case_id = #{caseId}")
    DangerInfo queryDangerInfo(@Param("caseId") int caseId);

    @Update("UPDATE db_danger_info SET transmission_route = #{transmissionRoute}, transmission_range = #{transmissionRange}, activity = #{activity} WHERE case_id = #{caseId}")
    int updateDangerInfo(DangerVo dangerVo);

    @Select("SELECT EXISTS(SELECT 1 FROM db_danger_info WHERE case_id = #{caseId})")
    boolean isContains(int caseId);

    @Insert("INSERT INTO db_danger_info (case_id, transmission_route, transmission_range, activity) VALUES (#{caseId}, #{transmissionRoute}, #{transmissionRange}, #{activity})")
    int addDanger(DangerVo dangerVo);
}
