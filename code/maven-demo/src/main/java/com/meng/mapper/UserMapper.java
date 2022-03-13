package com.meng.mapper;

import com.meng.pojo.User;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有
     *
     * @return
     */
    List<User> selectAll();

}
