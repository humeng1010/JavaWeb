package com.meng.mapper;

import com.meng.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {


//    //注解开发
//    @Select("select * from tb_brand;")
//    List<Brand> selectAll();
//
//    @Select("select * from tb_brand where id = #{id} ;")
//    Brand selectById();

    /**
     * 查询所有
     *
     * @return
     */
    List<Brand> getAll();


    /**
     * 查看详情
     */
    Brand getById(int id);

    /**
     * 条件查询
     * * 参数接收：
     * 1. 散装参数：如果方法中有多个参数（传到Mapper的映射的xml SQL文件 不知道哪个对应哪个），所以需要使用@Parma("SQL参数占位符")
     * 2. 对象参数
     * 3. map集合参数
     * <p>
     * 1. 散装参数：多个参数（mybatis会把多个参数封装为Mao集合）
     * 封装为Map集合(底层原理）
     * map值为参数值，而不是@Param注解中的
     * 如果不写@Param map的键是默认的[arg0,arg1,arg3...]或者[param0,param1,param2...]
     * map.put("arg0",status)
     * map.put("param0",status)
     * <p>
     * map.put("arg1",companyName)
     * map.put("param1",companyName)
     * <p>
     * map.put("arg2",brandName)
     * map.put("param2",brandName)
     *
     * @param status
     * @param companyName
     * @param brandName
     * @return
     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    //    List<Brand> selectByCondition(Brand brand);
//

    /**
     * 动态查询（动态SQL）
     *
     * @param map
     * @return
     */
    List<Brand> selectByCondition(Map<String, Object> map);

    /**
     * 单条件的动态查询
     *
     * @param brand
     * @return
     */
    List<Brand> selectByConditionSingle(Brand brand);

    /**
     * 添加
     */
    void add(Brand brand);

//    int update(Brand brand);

    /**
     * 动态修改（修改部分）动态SQL
     *
     * @param brand
     * @return
     */
    int update(Brand brand);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") int[] ids);


}
