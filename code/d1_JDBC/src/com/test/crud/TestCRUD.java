package com.test.crud;

import com.test.pojo.Brand;
import com.test.utils.ConnectionUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestCRUD {
    /**
     * 查询所有
     */
    @Test
    public void getAll() throws Exception {
        //通过数据库连接池工具类获取连接对象
        Connection connection = ConnectionUtils.connection();

        String sql = "select * from tb_brand;";
        //获取预编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Brand> brands = new ArrayList<>();

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            String companyName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            Brand brand = new Brand(id, brandName, companyName, ordered, description, status);

            brands.add(brand);
        }
        System.out.println(brands);
        preparedStatement.close();
        connection.close();
    }

    /**
     * 添加
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        //模拟来自web的数据
        String brandName = "大米";//商品的名称
        String companyName = "大米";//公司名称
        int ordered = 100;//排序
        String description = "好吃";//描述
        int status = 1;//开关

        Connection connection = ConnectionUtils.connection();

        String sql = "insert into tb_brand (brand_name, company_name, ordered, description, status) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);

        int count = preparedStatement.executeUpdate();//影响的行数

        System.out.println(count > 0);
        preparedStatement.close();
        connection.close();

    }


    /**
     * 修改
     */
    @Test
    public void update() throws Exception {
        int id = 4;
        String brandName = "测试数据";//商品的名称
        String companyName = "大米";//公司名称
        int ordered = 1000;//排序
        String description = "好吃";//描述
        int status = 1;//开关
        Connection connection = ConnectionUtils.connection();
        String sql = "update tb_brand set brand_name = ?,company_name = ?,ordered = ?, description = ?,status = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);
        preparedStatement.setInt(6, id);

        int count = preparedStatement.executeUpdate();//影响的行数

        System.out.println(count > 0);
        preparedStatement.close();
        connection.close();


    }

    @Test
    public void remove() throws Exception {
        int id = 4;
        Connection connection = ConnectionUtils.connection();

        String sql = "delete from tb_brand where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int count = preparedStatement.executeUpdate();
        System.out.println(count > 0);
        getAll();//查询所有
        preparedStatement.close();
        connection.close();

    }

}
