package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{id}")
    Credential find(int id);

    @Insert("INSERT INTO CREDENTIALS (userId, url, username, key, password) " +
            "VALUES (#{userId}, #{url}, #{username}, #{key}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int create(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, " +
            "username = #{username}, " + "password = #{password}," +
            "key = #{key}" +
            "WHERE credentialId = #{credentialId} ")
    void update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{id}")
    void deleteCredential(int id);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> findByUser(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url} AND username = #{username}")
    Credential findByURLandUsername(String url, String username);
}
