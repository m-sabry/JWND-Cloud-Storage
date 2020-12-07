package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE userId = #{id}")
    User find(int id);

    @Select("SELECT * FROM USERS WHERE userName = #{userName}")
    User findByUserName(String userName);

    @Insert("INSERT INTO USERS (userName, salt, password, firstName, lastName) " +
            "VALUES (#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int create(User user);

    @Update("UPDATE USERS SET userId = #{userId}, userName = #{userName}, " +
            "salt = #{salt}, password = #{password}, firstName = #{firstName}, " +
            "lastName = #{lastName}")
    void update(User user);

    @Delete("DELETE FROM USERS WHERE userId = #{id}")
    void deleteUser(int id);

}
