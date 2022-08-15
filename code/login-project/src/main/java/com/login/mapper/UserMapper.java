package com.login.mapper;

import com.login.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    User selectUser(User user);

    @Select("select count(*) from tb_user where username = #{username} ;")
    int selectByUsernameInt(String username);

    @Insert("insert into tb_user (username, password) values (#{username},#{password});")
    void addUser(User user);
}
