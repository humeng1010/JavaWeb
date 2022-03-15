package com.meng.test;

import com.meng.mapper.BrandMapper;
import com.meng.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatisAnnotationDev {
    @Test
    public void selectAll() throws Exception {
        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();

        System.out.println(brands);

        sqlSession.close();

    }

    /**
     * 根据id查找
     *
     * @throws Exception
     */
    @Test
    public void selectById() throws Exception {
        //模拟从前端web接收的id
        int id = 11;

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过sqlSessionFactory工厂得到sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectById(id);

        System.out.println(brand);

        sqlSession.close();


    }

    @Test
    public void removeById() throws Exception {
        int id = 11;//删除的id

        //1、获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        int count = brandMapper.removeById(id);

        System.out.println(count);

        sqlSession.commit();//提交事务


        sqlSession.close();


    }
}
