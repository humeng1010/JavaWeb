package com.test.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 数据库连接池工具类
 */
public class ConnectionUtils {
    public static Connection connection() {

        Connection connection = null;
        try {
            //3、加载配置文件
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/druid.properties"));

            //4、获取连接池对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

            //5、获取数据库连接 Connection
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }
}
