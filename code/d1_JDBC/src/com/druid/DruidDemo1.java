package com.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.pojo.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid数据库连接池演示
 */
public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
        //1、导入jar包

        //2、定义配置文件

        //3、加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("d1_JDBC/src/druid.properties"));

        //4、获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5、获取数据库连接 Connection
        Connection connection = dataSource.getConnection();

//        System.out.println(connection);//打印一下连接对象检查是否连接成功


        //根据Uid查询用户
        int Uid = 2;
        String sql = "select * from emp where id = ?";//SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//预编译sql

        preparedStatement.setInt(1, Uid);//设置？占位符的参数
        ResultSet resultSet = preparedStatement.executeQuery();//执行查询的sql

        while (resultSet.next()) {    //将光标向下一行查询结果（起始光标在内容的上一行，也就是在表头）
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.println(id + name + age);
        }


        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
