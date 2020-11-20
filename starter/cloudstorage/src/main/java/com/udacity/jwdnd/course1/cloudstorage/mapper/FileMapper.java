package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    User find(int id);

    @Insert("INSERT INTO FILES (fileId, userName, salt, password, firstName, lastName) " +
            "VALUES (#{fileId}, #{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int create(User user);

    @Update("UPDATE FILES SET fileId = #{fileId}, userName = #{userName}, " +
            "salt = #{salt}, password = #{password}, firstName = #{firstName}, " +
            "lastName = #{lastName}")
    void update(User user);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    void deleteFile(int id);
}
