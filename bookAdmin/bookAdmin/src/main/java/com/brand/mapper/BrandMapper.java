package com.brand.mapper;

import com.brand.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_brand;")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("insert into tb_brand (brand_name, company_name, ordered, description, status) values (#{brandName},#{companyName},#{ordered},#{description},#{status});")
    void addBrand(Brand brand);

    void updateBrand(Brand brand);
}
