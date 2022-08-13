package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC的快速入门
 */
public class JDBCDemo1 {
    public static void main(String[] args) throws Exception {
        //1、注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");// MySQL5之后就可以不用写这行注册驱动代码了

        //2、获取连接
//        String url = "jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
        String url = "jdbc:mysql://localhost:3306/db1";
        String username = "root";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3、定义sql语句
        String sql = "update db1.emp set age = 30 where id = 1 ;";
        //4、获取执行sql的对象 Statement
        Statement statement = connection.createStatement();
        //5、执行sql
        int count = statement.executeUpdate(sql);
        //6、处理结果
        System.out.println("受影响的行数：" + count);


        //7、释放资源
        statement.close();
        connection.close();


    }
}
