package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE noteId = #{id}")
    User find(int id);

    @Insert("INSERT INTO NOTES (noteId, userName, salt, password, firstName, lastName) " +
            "VALUES (#{noteId}, #{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int create(User user);

    @Update("UPDATE NOTES SET noteId = #{noteId}, userName = #{userName}, " +
            "salt = #{salt}, password = #{password}, firstName = #{firstName}, " +
            "lastName = #{lastName}")
    void update(User user);

    @Delete("DELETE FROM NOTES WHERE noteId = #{id}")
    void deleteNote(int id);
}
