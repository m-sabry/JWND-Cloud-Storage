package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    File find(int id);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) " +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int create(File file);

    @Update("UPDATE FILES SET fileName = #{fileName}, " +
            "contentType = #{contentType}, fileSize = #{fileSize}, fileDate = #{fileData}")
    void update(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    void deleteFile(int id);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> findByUser(int userId);
}
