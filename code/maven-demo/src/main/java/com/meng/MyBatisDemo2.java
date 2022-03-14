package com.meng;

import com.meng.mapper.UserMapper;
import com.meng.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * MyBatis代理开发
 */
public class MyBatisDemo2 {
    public static void main(String[] args) throws Exception {
        //1、加载MyBatis的核心配置文件，获取SqlSessionFactory对象(官网直接复制）
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取SqlSession 对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3、执行sql语句
        //List<User> brands = sqlSession.selectList("com.meng.MyBatisDemo.selectAll");
        //3.1获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Brand> brands = userMapper.selectAll();

        //4、打印users
        System.out.println(brands);

        //5、释放资源
        sqlSession.close();
    }
}
