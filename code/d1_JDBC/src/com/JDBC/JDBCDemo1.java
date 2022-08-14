package com.JDBC;

import com.pojo.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * JDBC的快速入门
 */
public class JDBCDemo1 {
    public static void main(String[] args) throws Exception {
        //1、注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");// MySQL5之后就可以不用写这行注册驱动代码了

        //2、获取连接
//        String url = "jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
        String url = "jdbc:mysql://localhost:3306/db1";
        String username = "root";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3、定义sql语句
        String sql = "update db1.emp set age = ? where id = ? ;";
        //4、获取执行sql的对象 Statement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 22);
        statement.setInt(2, 2);

        //5、执行sql
        int count = statement.executeUpdate();
        //6、处理结果
        System.out.println("受影响的行数：" + count);
        PreparedStatement statement1 = connection.prepareStatement("select * from db1.emp where id = ?;");

        System.out.println("请输入查询的id");
        statement1.setInt(1, new Scanner(System.in).nextInt());

        ResultSet resultSet = statement1.executeQuery();
        ArrayList<Emp> emps = new ArrayList<>();
        while (resultSet.next()) {
            Emp emp = new Emp();
            emp.setId(resultSet.getInt(1));
            emp.setName(resultSet.getString(2));
            emp.setAge(resultSet.getInt(3));
            emp.setDepId(resultSet.getInt(4));
            emps.add(emp);
        }
        System.out.println(emps);

        PreparedStatement statement2 = connection.prepareStatement("select * from db1.emp where name like ?;");
        statement2.setString(1, "张%");
        ResultSet resultSet1 = statement2.executeQuery();
        while (resultSet1.next()) {
            Emp emp = new Emp();
            emp.setId(resultSet1.getInt(1));
            emp.setName(resultSet1.getString(2));
            emp.setAge(resultSet1.getInt(3));
            emp.setDepId(resultSet1.getInt(4));
            emps.add(emp);
        }
        System.out.println(emps);


        //7、释放资源
        statement.close();
        connection.close();


    }
}
