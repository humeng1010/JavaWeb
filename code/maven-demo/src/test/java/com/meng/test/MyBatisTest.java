package com.meng.test;

import com.meng.mapper.BrandMapper;
import com.meng.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    /**
     * 查询所有
     *
     * @throws Exception
     */
    @Test
    public void testGetAll() throws Exception {
        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.getAll();

        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testGetById() throws Exception {
        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.getById(1);
        System.out.println(brand);
        /**
         *                                                               select * from tb_brand where id = #{id};
         * [DEBUG] 12:06:38.183 [main] c.m.m.B.getById - ==>  Preparing: select * from tb_brand where id = ?;
         * [DEBUG] 12:06:38.203 [main] c.m.m.B.getById - ==> Parameters: 1(Integer)
         * [DEBUG] 12:06:38.222 [main] c.m.m.B.getById - <==      Total: 1
         * Brand{id=1, brandName='三只松鼠', companyName='三只松鼠股份有限公司', ordered=5, description='好吃不上火', status=0}
         */

        sqlSession.close();
    }


    /**
     * 条件查询
     *
     * @throws Exception
     */
    @Test
    public void selectByCondition() throws Exception {
        //接收参数
        Integer status = 1;
        String companyName = "华为";//%华为%
        String brandName = "华为";//%华为%
        //处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        Map<String, Object> map = new HashMap<>();
        //map.put("status", status);
        map.put("companyName", companyName);//Preparing: select * from tb_brand WHERE company_name like ?
        //map.put("brandName", brandName);  //Preparing: select * from tb_brand where status = ? and company_name like ?

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);

        sqlSession.close();

    }

    @Test
    public void selectByConditionSingle() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";//%华为%
        //处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";
        Brand brand = new Brand();
        //brand.setStatus(status);
        //brand.setCompanyName(companyName);
        //brand.setBrandName(brandName);

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);

        sqlSession.close();


    }

    /**
     * 添加
     *
     * @throws IOException
     */
    @Test
    public void add() throws IOException {
        int status = 1;
        String companyName = "测试数据";
        String brandName = "测试数据";
        String description = "测试数据";
        int ordered = 100;

        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.add(brand);
        System.out.println(brand.getId());


        sqlSession.commit();//提交事务
        sqlSession.close();

    }

    /**
     * 修改
     *
     * @throws IOException
     */
    @Test
    public void update() throws IOException {
        int id = 2;//要修改的id

        int status = 1;
        String companyName = "测试数据11111111";
        String brandName = "测试数据";
        String description = "测试数据";
        int ordered = 100000;

        Brand brand = new Brand();
        brand.setId(id);
        brand.setStatus(status);
        //brand.setCompanyName(companyName);
        //brand.setBrandName(brandName);
        //brand.setDescription(description);
        brand.setOrdered(ordered);

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int count = brandMapper.update(brand);
        System.out.println(count);


        sqlSession.commit();//提交事务
        sqlSession.close();

    }

    @Test
    public void deleteById() throws IOException {
        int id = 10;//要删除的id

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int count = brandMapper.deleteById(id);
        System.out.println(count);


        sqlSession.commit();//提交事务
        sqlSession.close();

    }

    @Test
    public void deleteByIds() throws IOException {
        int[] ids = {12, 13, 14, 15, 16};//要删除的id

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int count = brandMapper.deleteByIds(ids);
        System.out.println(count);


        sqlSession.commit();//提交事务
        sqlSession.close();

    }
}
