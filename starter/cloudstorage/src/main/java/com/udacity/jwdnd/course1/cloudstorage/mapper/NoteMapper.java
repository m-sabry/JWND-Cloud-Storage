package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE noteId = #{id}")
    Note find(int id);

    @Insert("INSERT INTO NOTES (userId, noteTitle, noteDescription) " +
            "VALUES (#{userId}, #{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int create(Note note);

    @Update("UPDATE NOTES SET noteId = #{noteId}, userName = #{userName}, " +
            "salt = #{salt}, password = #{password}, firstName = #{firstName}, " +
            "lastName = #{lastName}")
    void update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{id}")
    void deleteNote(int id);

    @Select("SELECT * FROM NOTES")
    List<Note> findAll();
}
