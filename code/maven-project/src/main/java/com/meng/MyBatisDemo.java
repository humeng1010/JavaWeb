package com.meng;

import com.meng.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.util.List;

public class MyBatisDemo {
    public static void main(String[] args) throws Exception {
        //1、加载MyBatis的核心配置文件，获取SqlSessionFactory对象(官网直接复制）
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("com.meng.mapper.UserMapper.selectAll");
        System.out.println(users);
        sqlSession.close();
    }
}
