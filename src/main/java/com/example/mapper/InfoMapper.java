package com.example.mapper;

import com.example.entity.Info;
import com.example.entity.Risk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface InfoMapper {

    @Insert({
            "<script>",
            "INSERT INTO db_info_input (id",
            "<if test='file1path != null'>, file1path</if>",
            "<if test='file2path != null'>, file2path</if>",
            "<if test='file3path != null'>, file3path</if>",
            "<if test='file4path != null'>, file4path</if>",
            "<if test='file5path != null'>, file5path</if>",
            "<if test='file6path != null'>, file6path</if>",
            "<if test='file7path != null'>, file7path</if>",
            "<if test='file8path != null'>, file8path</if>",
            "<if test='file9path != null'>, file9path</if>",
            "<if test='file10path != null'>, file10path</if>",
            "<if test='file11path != null'>, file11path</if>",
            "<if test='file12path != null'>, file12path</if>",
            "<if test='file13path != null'>, file13path</if>",
            "<if test='file14path != null'>, file14path</if>",
            "<if test='file15path != null'>, file15path</if>",
            "<if test='file16path != null'>, file16path</if>",
            "<if test='file17path != null'>, file17path</if>",
            "<if test='file18path != null'>, file18path</if>",
            "<if test='file19path != null'>, file19path</if>",
            ") VALUES (#{id}",
            "<if test='file1path != null'>, #{file1path}</if>",
            "<if test='file2path != null'>, #{file2path}</if>",
            "<if test='file3path != null'>, #{file3path}</if>",
            "<if test='file4path != null'>, #{file4path}</if>",
            "<if test='file5path != null'>, #{file5path}</if>",
            "<if test='file6path != null'>, #{file6path}</if>",
            "<if test='file7path != null'>, #{file7path}</if>",
            "<if test='file8path != null'>, #{file8path}</if>",
            "<if test='file9path != null'>, #{file9path}</if>",
            "<if test='file10path != null'>, #{file10path}</if>",
            "<if test='file11path != null'>, #{file11path}</if>",
            "<if test='file12path != null'>, #{file12path}</if>",
            "<if test='file13path != null'>, #{file13path}</if>",
            "<if test='file14path != null'>, #{file14path}</if>",
            "<if test='file15path != null'>, #{file15path}</if>",
            "<if test='file16path != null'>, #{file16path}</if>",
            "<if test='file17path != null'>, #{file17path}</if>",
            "<if test='file18path != null'>, #{file18path}</if>",
            "<if test='file19path != null'>, #{file19path}</if>",
            ")",
            "</script>"
    })
    int createInfo(
            int id,
            String file1path, String file2path, String file3path, String file4path,
            String file5path, String file6path, String file7path, String file8path,
            String file9path, String file10path, String file11path, String file12path,
            String file13path, String file14path, String file15path, String file16path,
            String file17path, String file18path, String file19path
    );


    @Select("SELECT * from db_info_input where id=${id}")
    Info queryInfo(int id);



    @Select("SELECT * FROM db_info_input WHERE id = #{id}")
    Info containInfoById(int id);

    @Update({
            "<script>",
            "UPDATE db_info_input",
            "<set>",
            "<if test='file1path != null and file1path != \"\"'>file1path = #{file1path},</if>",
            "<if test='file2path != null and file2path != \"\"'>file2path = #{file2path},</if>",
            "<if test='file3path != null and file3path != \"\"'>file3path = #{file3path},</if>",
            "<if test='file4path != null and file4path != \"\"'>file4path = #{file4path},</if>",
            "<if test='file5path != null and file5path != \"\"'>file5path = #{file5path},</if>",
            "<if test='file6path != null and file6path != \"\"'>file6path = #{file6path},</if>",
            "<if test='file7path != null and file7path != \"\"'>file7path = #{file7path},</if>",
            "<if test='file8path != null and file8path != \"\"'>file8path = #{file8path},</if>",
            "<if test='file9path != null and file9path != \"\"'>file9path = #{file9path},</if>",
            "<if test='file10path != null and file10path != \"\"'>file10path = #{file10path},</if>",
            "<if test='file11path != null and file11path != \"\"'>file11path = #{file11path},</if>",
            "<if test='file12path != null and file12path != \"\"'>file12path = #{file12path},</if>",
            "<if test='file13path != null and file13path != \"\"'>file13path = #{file13path},</if>",
            "<if test='file14path != null and file14path != \"\"'>file14path = #{file14path},</if>",
            "<if test='file15path != null and file15path != \"\"'>file15path = #{file15path},</if>",
            "<if test='file16path != null and file16path != \"\"'>file16path = #{file16path},</if>",
            "<if test='file17path != null and file17path != \"\"'>file17path = #{file17path},</if>",
            "<if test='file18path != null and file18path != \"\"'>file18path = #{file18path},</if>",
            "<if test='file19path != null and file19path != \"\"'>file19path = #{file19path},</if>",
            "</set>",
            "WHERE id = ${id}",
            "</script>"
    })
    void updateInfo(int id,
                    String file1path, String file2path, String file3path,
                    String file4path, String file5path, String file6path,
                    String file7path, String file8path, String file9path,
                    String file10path, String file11path, String file12path,
                    String file13path, String file14path, String file15path,
                    String file16path, String file17path, String file18path,
                    String file19path);
}

