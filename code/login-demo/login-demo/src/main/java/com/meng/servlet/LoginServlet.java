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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1、接收用户名和密码
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
        //3、获取userMapper对象(Mapper代理开发）
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //4、通过userMapper调用查找方法
        User user = userMapper.select(username, password);
        System.out.println("数据库中该用户的信息：" + user);
        //5、释放资源
        sqlSession.close();

        //获取对应的字符输出流，并设置对应的contentType
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (Objects.isNull(user)) {
            //数据库中没有此数据，登入失败
            writer.write("<h1>用户名或密码错误，或者没有此用户，登陆失败<h1/>");

        } else {
            //数据库中有此人，登入成功
            writer.write("<h1>登入成功<h1/>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
