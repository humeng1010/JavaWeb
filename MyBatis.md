# MyBatis

## 什么是MyBatis？

- MyBatis是一款优秀的**持久层**框架，用于简化JDBC开发
- 官网：https://mybatis.org/mybatis-3/zh/index.html



### 持久层

- 负责将**数据**保存到**数据库**的那一层代码
- JavaEE三层架构：表现层、业务层、**持久层**
- 表现层：页面展示
- 业务层：逻辑处理
- 持久层：数据持久化（保存到数据库）



## MyBatis快速入门

### 查询User表中的所有数据

1. 创建User表，添加数据
2. 创建模块，导入坐标
3. 编写MyBatis核心配置文件 --> 替换连接信息 解决硬编码问题
4. 编写SQL映射文件 --> 统一SQL管理，解决硬编码问题
5. 编码：
   1. 定义POJO类
   2. 加载核心配置文件，获取SqlSessionFactory对象
   3. 获取SqlSession对象，执行SQL语句
   4. 释放资源



1. 配置pom.xml文件(pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!--    当前项目的jar包-->
    <groupId>com.meng</groupId>
    <artifactId>maven-demo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!--        MyBatis 依赖jar包-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.9</version>
        </dependency>

        <!--    MySQL驱动jar包-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>

        <!--        druid数据库连接池jar包-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.8</version>
        </dependency>

        <!--        Junit测试jar包-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!--        logback依赖坐标-->
        <!-- 添加slf4j日志api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.20</version>
        </dependency>
        <!-- 添加logback-classic依赖 -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>
        <!-- 添加logback-core依赖 -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.3</version>
        </dependency>
    </dependencies>


</project>
```

2. logback.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <configuration>
       <!--
           CONSOLE ：表示当前的日志信息是可以输出到控制台的。
       -->
       <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
           <encoder>
               <pattern>[%level] %blue(%d{HH:mm:ss.SSS}) %cyan([%thread]) %boldGreen(%logger{15}) - %msg %n</pattern>
           </encoder>
       </appender>
   
       <logger name="com.meng" level="DEBUG" additivity="false">
           <appender-ref ref="Console"/>
       </logger>
   
   
       <!--
   
         level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF
        ， 默认debug
         <root>可以包含零个或多个<appender-ref>元素，标识这个输出位置将会被本日志级别控制。
         -->
       <root level="DEBUG">
           <appender-ref ref="Console"/>
       </root>
   </configuration>
   ```

   

3. 配置mybatis-config配置文件(mybatis-config.xml)

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <!--                连接信息-->
                   <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql:///mybatis"/>
                   <property name="username" value="root"/>
                   <property name="password" value="12345678"/>
               </dataSource>
           </environment>
       </environments>
       <mappers>
           <!--        加载SQL的映射文件-->
           <mapper resource="UserMapper.xml"/>
       </mappers>
   </configuration>
   ```

4. SQL的映射文件(UserMapper.xml)

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <!--
       namespace:名称空间
   -->
   <mapper namespace="com.meng.MyBatisDemo">
       <!--    id：唯一标识
               resultType:返回结果的类型（将来数据返回的是什么类型就写什么类型）
       -->
       <select id="selectAll" resultType="com.meng.pojo.User">
           select *
           from tb_user;
       </select>
   </mapper>
   ```

5. POJO(User)

   ```java
   package com.meng.pojo;
   
   public class User {
       private Integer id;
       private String username;
       private String password;
       private String gender;
       private String addr;
   
       public User() {
       }
   
       public User(Integer id, String username, String password, String gender, String addr) {
           this.id = id;
           this.username = username;
           this.password = password;
           this.gender = gender;
           this.addr = addr;
       }
   
       public Integer getId() {
           return id;
       }
   
       public void setId(Integer id) {
           this.id = id;
       }
   
       public String getUsername() {
           return username;
       }
   
       public void setUsername(String username) {
           this.username = username;
       }
   
       public String getPassword() {
           return password;
       }
   
       public void setPassword(String password) {
           this.password = password;
       }
   
       public String getGender() {
           return gender;
       }
   
       public void setGender(String gender) {
           this.gender = gender;
       }
   
       public String getAddr() {
           return addr;
       }
   
       public void setAddr(String addr) {
           this.addr = addr;
       }
   
       @Override
       public String toString() {
           return "User{" +
                   "id=" + id +
                   ", username='" + username + '\'' +
                   ", password='" + password + '\'' +
                   ", gender='" + gender + '\'' +
                   ", addr='" + addr + '\'' +
                   '}';
       }
   }
   
   ```

6. MyBatisDemo.java

   ```java
   package com.meng;
   
   import com.meng.pojo.User;
   import org.apache.ibatis.io.Resources;
   import org.apache.ibatis.session.SqlSession;
   import org.apache.ibatis.session.SqlSessionFactory;
   import org.apache.ibatis.session.SqlSessionFactoryBuilder;
   
   import java.io.InputStream;
   import java.util.List;
   
   /**
    * MyBatis的快速入门
    */
   public class MyBatisDemo {
       public static void main(String[] args) throws Exception {
           //1、加载MyBatis的核心配置文件，获取SqlSessionFactory对象(官网直接复制）
           String resource = "mybatis-config.xml";
           InputStream inputStream = Resources.getResourceAsStream(resource);
           SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
   
           //2、获取SqlSession 对象，用它来执行sql
           SqlSession sqlSession = sqlSessionFactory.openSession();
   
           //3、执行sql语句
           List<User> users = sqlSession.selectList("com.meng.MyBatisDemo.selectAll");
   
           //4、打印users
           System.out.println(users);
   
           //5、释放资源
           sqlSession.close();
       }
   }
   
   ```



## Mapper代理开发

- 目的

  - 解决原生方式中的硬编码问题

    例如：

    ```java
    //2、获取SqlSession 对象，用它来执行sql
            SqlSession sqlSession = sqlSessionFactory.openSession();
    
            //3、执行sql语句
            List<User> users = sqlSession.selectList("com.meng.MyBatisDemo.selectAll");
    
    
    ```

    通过Mapper代理可以简化成这样

    ```java
    //3.1获取UserMapper接口的代理对象
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.selectAll();
    
    ```

  - 简化后期执行SQL

### 步骤

1. 定义与SQL映射文件同名的Mapper接口，并且将Mapper接口和SQL映射文件放置在同一目录下
2. 设置SQL映射文件的namespace属性为Mapper接口的全限名
3. 在Mapper接口中定义方法，方法名就是SQL映射文件中SQL语句的id，并且保持参数类型和返回值类型一致
4. 编码：
   1. 通过SQLSession的getMapper方法获取Mapper接口的代理对象
   2. 调用对应的方法完成sql的执行

细节：如果Mapper接口名称和SQL映射文件名称相同，并且在同一目录下，则可以使用包扫描的方式简化SQL映射文件的加载









## MyBatis核心配置文件

mybatis-config

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--    起别名，以后在Mapper映射文件中SQL语句上面的resultType返回值类型就可以不带包名了，可以直接使用返回值类型
                    而且不区分大小写了
    -->
    <typeAliases>
        <package name="com.meng.pojo"/>
    </typeAliases>


    <!--
           environments：配置数据库连接环境信息，可以配置多个environment，将来使用哪个数据库可以直接修改environments的default
    -->
    <environments default="development">
        <!--        开发环境的数据库-->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!--                连接信息-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>

        <!--        测试环境的数据库-->
        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!--                连接信息-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>

    </environments>
    <mappers>
        <!--        加载SQL的映射文件-->
        <!--        <mapper resource="com/meng/mapper/UserMapper.xml"/>-->
        <!--        Mapper 代理方式（包扫描）-->
        <package name="com.meng.mapper"/>
    </mappers>
</configuration>
```



**细节**：**配置各个标签的时候**，**需要遵守前后的顺序**

