package com.JDBC;

import org.junit.Test;

import java.sql.*;

public class JDBCDemo6 {
    @Test
    public void testSQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from tb_test where name = 'zhangsan' and password='123';");
//        SQL注入密码为 ' or '1'='1
        ResultSet resultSet = statement.executeQuery("select * from tb_test where name = 'zhang' and password='' or '1'='1';");
        if (resultSet.next()) {
            System.out.println("查询成功");
        } else {
            System.out.println("查询失败");
        }
        PreparedStatement statement1 = connection.prepareStatement("select * from tb_test where name = ? and password=?");
        statement1.setString(1, "张三");
        statement1.setString(2, "'or'1'='1");
        ResultSet resultSet1 = statement1.executeQuery();
        if (resultSet1.next()) {
            System.out.println("查询成功");
        } else {
            System.out.println("查询失败");
        }


    }
}
