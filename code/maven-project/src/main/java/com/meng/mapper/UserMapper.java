package com.meng.mapper;

import com.meng.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    User selectById();

}
