package com.meng.mapper;

import com.meng.pojo.Brand;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有
     *
     * @return
     */
    List<Brand> selectAll();

    /**
     * @return
     */
    Brand getById();


}
