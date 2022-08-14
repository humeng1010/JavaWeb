package com.meng.test;

import com.meng.mapper.BrandMapper;
import com.meng.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class MyBatisTest {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
//        获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
//        获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(2);
        System.out.println(brand);
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() {
//        获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        String brandName = "华为";
        String companyName = "华为";
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";

        Brand brand = new Brand();
        brand.setStatus(1);
        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);

//        List<Brand> brands = mapper.selectByCondition(1, companyName, brandName);
        List<Brand> brands = mapper.selectByConditionSingle(brand);
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testAddBrand() {
//        获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);


        Brand brand = new Brand();
        brand.setStatus(1);
        brand.setBrandName("pp");
        brand.setCompanyName("ppp");
        brand.setDescription("好极了");

//        List<Brand> brands = mapper.selectByCondition(1, companyName, brandName);
        mapper.addBrand(brand);

        System.out.println(brand.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateBrand() {
//        获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);


        Brand brand = new Brand();
        brand.setId(2);
        brand.setStatus(1);
        brand.setBrandName("pp");
        brand.setCompanyName("ppp");
        brand.setDescription("好极了");

//        List<Brand> brands = mapper.selectByCondition(1, companyName, brandName);
        int i = mapper.updateBrand(brand);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById() {
//        获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(10);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() {
//        获取sqlSession对象
        int[] arr = {71, 72, 73};
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(arr);

        sqlSession.commit();
        sqlSession.close();
    }

}
