package com.meng.servlet;

import com.meng.mapper.UserMapper;
import com.meng.pojo.User;
import com.meng.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

        //2、调用mybatis完成查询
        //1、加载MyBatis的核心配置文件，获取SqlSessionFactory对象(官网直接复制）
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


        User user = userMapper.selectUsername(username);
        System.out.println(Objects.isNull(user) ? "可注册" : "不可注册");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if (Objects.isNull(user)) {


            //可以使用该用户名
            writer.write("<h1>注册成功<h1/>");
            User user1 = new User();
            user1.setUsername(username);
            user1.setPassword(password);
            userMapper.add(user1);

            sqlSession.commit();
            sqlSession.close();


        } else {

            //用户名被占用不可使用
            writer.write("<h1>注册失败<h1/>");


        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
