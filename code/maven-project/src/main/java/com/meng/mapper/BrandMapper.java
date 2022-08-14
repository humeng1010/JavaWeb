package com.meng.mapper;

import com.meng.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    @Select("select * from tb_brand;")
    List<Brand> selectAll();

    Brand selectById(int id);

    /**
     * 多条件查询
     *
     * @param status
     * @param companyName
     * @param brandName
     * @return
     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    List<Brand> selectByCondition(Brand brand);

    List<Brand> selectByCondition(Map<String, Object> map);

    /**
     * 单条件查询
     *
     * @param brand
     * @return
     */
    List<Brand> selectByConditionSingle(Brand brand);

    void addBrand(Brand brand);

    int updateBrand(Brand brand);

    void deleteById(int id);

    void deleteByIds(@Param("ids") int[] ids);
}
