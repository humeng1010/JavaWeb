package com.JDBC;

import com.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC的快速入门
 */
public class JDBCDemo3 {
    public static void main(String[] args) throws Exception {
        //1、注册驱动
        //Class.forName("com.mysql.cj.jdbc.Driver");MySQL5之后就可以不用写这行注册驱动代码了

        //2、获取连接
        String url = "jdbc:mysql://localhost:3306/db2";
        String username = "root";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3、定义sql语句
        String sql = "select * from tb_user;";
        //4、获取执行sql的对象 Statement
        Statement statement = connection.createStatement();
        //5、执行sql
        ResultSet resultSet = statement.executeQuery(sql);

        List<User> list = new ArrayList<>();

        while (resultSet.next()) {

            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String UserPassword = resultSet.getString(3);
            User user = new User(id, name, UserPassword);
            list.add(user);

        }

        //6、处理结果
        System.out.println(list);

        //7、释放资源
        resultSet.close();
        statement.close();
        connection.close();


    }
}
