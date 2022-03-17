package com.meng.service.impl;

import com.meng.dao.UserMapper;
import com.meng.pojo.User;
import com.meng.service.UserService;
import com.meng.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User select(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.select(username, password);

        sqlSession.close();

        return user;
    }
}
