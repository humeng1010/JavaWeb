package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC的快速入门
 */
public class JDBCDemo5 {
    public static void main(String[] args) throws Exception {
        //1、注册驱动
        //Class.forName("com.mysql.cj.jdbc.Driver");MySQL5之后就可以不用写这行注册驱动代码了

        //2、获取连接
        String url = "jdbc:mysql://localhost:3306/db2&useServerPrepStmts=true";
        String username = "root";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, username, password);

        String name = "李四";
        String password2 = "'or'1'='1";//再尝试SQL注入，发现登入失败

        //3、定义sql语句
        String sql = "select * from tb_user where username = ? and password = ?;";
        //4、获取执行sql的对象 Statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5、替换？占位符
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password2);//预编译会把密码自动转义为  \'or\'1\'=\'1  保持原有的样子
        //6、执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        //7、结果处理
        if (resultSet.next()) {
            System.out.println("success");
        } else {
            System.out.println("false");
        }
        //8、释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();


    }
}
