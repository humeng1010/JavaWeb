package com.meng.mapper;

import com.meng.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 用户登陆
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_user where username = #{username} and password = #{password};")
    User select(@Param("username") String username, @Param("password") String password);

    @Select("select * from tb_user  where username = #{usernaem} ;")
    User selectUsername(String username);

    @Insert("insert into tb_user (username, password) values (#{username},#{password} );")
    int add(User user);
}
