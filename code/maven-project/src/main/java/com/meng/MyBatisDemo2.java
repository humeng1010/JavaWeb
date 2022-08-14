package com.meng;

import com.meng.mapper.UserMapper;
import com.meng.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;

public class MyBatisDemo2 {
    public static void main(String[] args) throws IOException {
        //1、加载MyBatis的核心配置文件，获取SqlSessionFactory对象(官网直接复制）
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();


//        获取UserMapper接口的代理对象(动态代理实现Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h))
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        通过代理对象调用selectAll接口方法(代理对象自动对我们的sql进行处理并封装返回结果)
        List<User> users = userMapper.selectAll();

        System.out.println(users);
        sqlSession.close();
    }
}
