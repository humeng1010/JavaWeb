# Web核心

JavaEE：Java企业版。指Java企业级开发的技术规范总和。包含13项技术规范：JDBC、JNDI、EJB、RMI、JSP、Servlet、XML、JMS、Java IDL、JTS、JTA、JavaMail、JAF

## HTTP

- HTTP的特点
  1. 基于TCP协议：面向连接，安全
  2. 基于请求-响应模型的：一次请求对应一次响应
  3. HTTP协议是无状态的协议：对于事务处理没有记忆能力，每次请求-响应都是独立的
     - 缺点：多次请求之间不能共享数据。Java中会使用会话技术（Cookie和Session）来解决这些问题
     - 优点：速度快



## HTTP-请求数据格式

- 请求数据分为3部分：
  1. **请求行**：请求数据的第一行。其中GET表示请求方式，/ 表示请求资源路径，HTTP/1.1 表示协议版本
  2. **请求头**：第二行开始，格式为key : value 形式
  3. **请求体**：**POST**请求的最后一部分，存放请求参数

<img src="https://gitee.com/xiaohugitee/tuchuang/raw/master/202203141557594.png" style="zoom:50%;" />





## HTTP响应数据格式

- 响应数据分为3部分
  1. **响应行**：响应数据的第一行，其中HTTP/1.1表示协议版本，200表示响应状态码，OK表示状态码描述
  2. **响应头**：第二行开始，格式为key : value 形式
  3. **响应体**：最后一部分，存放数据

<img src="https://gitee.com/xiaohugitee/tuchuang/raw/master/202203141603052.png" style="zoom:50%;" />

响应状态码

| 状态码分类 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| 1xx        | 响应中——临时状态码，表示请求已接受，告诉客户端应该继续请求或者如果它已完成则忽略它 |
| 2xx        | 成功——表示请求已经被成功接收，处理完成                       |
| 3xx        | 重定向——重定向到其他地方：它让客户端再发起一个请求以完成整个处理 |
| 4xx        | 客户端错误——处理发生错误，责任在客户端，如：客户端的请求一个不存在的资源，客户端未被授权，禁止访问等 |
| 5xx        | 服务器端错误——处理发生错误，责任在服务端，如：服务端抛出异常，路由错误，HTTP版本不支持 |





## Apache Tmocat

### 概述

- Tomcat是Apache软件基金会的一个核心项目，是一个开源的免费的轻量级的web服务器，支持Servlet/JSP少量JavaEE规范
- Tomcat称为Web容器、Servlet容器。Servlet需要依赖于Tomcat才能运行
- 官网：https://tomcat.apache.org

#### 总结：

1. web服务器的作用？
   - 封装HTTP协议操作、开发简单
   - 可以将web项目部署到服务器中，对外提供网上浏览服务
2. Tomcat是一个开源的免费的轻量级的web服务器，支持Servlet/JSP少量JavaEE规范也称为Web容器、Servlet容器。

### IDEA中创建Maven Web项目

- 使用骨架
  1. 选择web项目骨架，创建项目
  2. 删除pom.xml中的多余的坐标
  3. 补齐缺失的目录结构

<img src="https://gitee.com/xiaohugitee/tuchuang/raw/master/202203151037930.png" style="zoom:50%;" />

<img src="https://gitee.com/xiaohugitee/tuchuang/raw/master/202203151042470.png" style="zoom:50%;" />

### IDEA中使用Tomcat

- 将本地的Tomcat集成到IDEA中，然后进行项目**部署**即可

<img src="https://gitee.com/xiaohugitee/tuchuang/raw/master/202203151052197.png" style="zoom: 50%;" />



- Maven插件使用Tomcat

  1. pom.xml中添加Tomcat插件

     ```xml
     		<build>
             <plugins>
                 <!--            tomcat的插件-->
                 <plugin>
                     <groupId>org.apache.tomcat.maven</groupId>
                     <artifactId>tomcat7-maven-plugin</artifactId>
                     <version>2.2</version>
                     <configuration>
                         <port>80</port>
                         <path>/</path>
                     </configuration>
                 </plugin>
             </plugins>
         </build>
     ```

     

  2. 使用Maven Helper插件快速启动项目，选中项目，右键--》Run Maven--》tomcat7：run



## Servlet

- Servlet是Java提供的动态web资源开发技术
- Servlet是JavaEE规范之一，其实就是一个接口，将来我们需要定义Servlet类实现Servlet接口，并由web服务器运行Servlet



### 快速入门

1. 创建 web 项目，导入Servlet 依赖坐标（**注意范围scope为provided运行时无效，因为tomcat中已经自带了Servlet的jar包，防止冲突）**

   ```xml
   <!--        导入Servlet依赖坐标-->
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>4.0.1</version>
               <scope>provided</scope>
           </dependency>
   ```

   

2. 创建：定义一个类，实现Servlet接口，并重写接口中的所有方法，并在 service 方法总共输入一句话

   ```java
   public class ServletDemo1 implements Servlet {
   
       @Override
       public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
           System.out.println("servlet hello world~你好");
   
       }
   
   ```

   

3. 配置：在类上是用 @WebServlet 注解，配置该Servlet的访问路径

   ```java
   @WebServlet("/demo1")//配置访问路径
   public class ServletDemo1 implements Servlet {
   ```

   

4. 访问：启动 Tomcat ，浏览器输入URL 访问该Servlet

   ```
   http://localhost:8080/tomcat-demo1/demo1
   ```



### Servlet执行流程

1. Servlet由谁创建？Servlet方法由谁调用？
   - Servlet由web服务器创建，Servlet方法由web服务器调用
2. 服务器怎么知道Servlet中一定有service方法？
   - 因为我们自定义的Servlet类是实现了Servlet接口并且重写其方法，而Servlet接口中有service方法



### Servlet生命周期

- 对象的生命周期是指一个对象从被创建到被销毁的过程

- Servlet运行在Servlet容器（web服务器）中，其生命周期由容器来管理，分为4个阶段：

  1. **加载和实例化**：**默认情况下**，当Servlet**第一次被访问**的时候，由容器创建Servlet对象（可以设置在服务器启动的时候创建对象节约第一次访问时间：@WebServlet(urlPatterns = "/demo",loadOnStartup = 1)默认值为-1，设置为大于等于0的数在服务器启动的时候创建Servlet对象并且执行init初始化方法，优先级0最大 ）
  2. **初始化**：在Servlet实例化之后（创建），容器将调用Servlet的**init()**方法初始化这个对象，完成一些如加载配置文件、创建连接等初始化工作，该方法只会被调用**一次**
  3. **请求处理**：**每次**请求（访问url）Servlet时，Servlet容器会调用Servlet的**service()**方法对请求进行处理
  4. **服务终止**：当需要释放内存或者容器关闭时，容器会调用Servlet实例的**destroy()**方法完成资源的释放，在destroy()方法调用之后，容器会释放这个Servlet实例，该实例随后会被Java的垃圾收集器回收



### Servlet方法介绍

- 初始化方法，在Servlet对象被创建时执行，只执行一次

  ```java
  void init(ServletConfig config)
  ```

- 提供服务方法，每次Servlet被访问，都会调用该方法

  ```java
  void service(ServletRequest req,ServletResponse res)
  ```

- 销毁方法，当Servlet被销毁时，调用该方法，在内存释放或服务关闭时销毁Servlet

  ```java
  void destroy()
  ```

- 获取ServletConfig对象

  ```java
  ServletConfig getServletConfig()
  ```

- 获取Servlet信息(返回作者信息等，很少使用，可以直接返回""空字符串)

  ```java
  String getServletInfo()
  ```



### Servlet体系结构

- Servlet是**根接口**有抽象实现类GenericServlet，这个抽象实现类又有**HttpServlet抽象实现类**（对HTTP协议封装的Servlet实现类）
- 我们将来都是开发B/S架构的web项目，都是针对HTTP协议，所以我们**自定义的Servlet**，会**继承HttpServlet**
- 



  

  











