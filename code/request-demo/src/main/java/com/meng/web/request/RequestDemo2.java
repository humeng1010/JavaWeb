package com.meng.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/req2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、解决乱码:POST。POST底层是通过getReader() 字符输入流获取数据，但是tomcat默认的获取流的数据的编码是ISO-8859-1的 所以读中文数据的时候乱码
        request.setCharacterEncoding("UTF-8");//设置字符输入流的编码
        //2、获取username
        String username = request.getParameter("username");
        //System.out.println(username);

        //1、get方式，底层获取请求参数和post不一样，所以不可以使用设置字符输入流的编码
        //  getQueryString() 返回的是字符串
        //  乱码原因：首先中文参数通过浏览器的HTTP协议发送到Tomcat中，
        //  而浏览器不支持中文，则会对中文的字符串做出处理，会对中文进行URL编码
        //  （ 浏览器数据：张三----浏览器对utf-8进行URL编码为----->%E5%BC%A0%E4%B8%89 ---传送到--> tomcat中 ）
        //  （tomcat7对URL解码 ： %E5%BC%A0%E4%B8%89-------ISO-8859-1解码 --- 产生乱码-------> å¼ ä¸）
        //  解决思路：既然tomcat通过ISO_8859_1解码产生乱码，但是底层的字符编码始终没有改变
        //   我们可以得到乱码的字符编码 ( getBytes(StandardCharsets.ISO_8859_1) ) 要通过StandardCharsets.ISO_8859_1进行解码获得字节编码为：
        //  [-27, -68, -96, -28, -72, -119]   就是 张三 对应的二进制的转为十进制的编码的字符集编码utf-8（一个汉字占三个字节）
        //  然后再通过 new String的构造器方法把字符集：[-27, -68, -96, -28, -72, -119] 按照utf-8编码得到 张三
        byte[] usernameBytes = username.getBytes(StandardCharsets.ISO_8859_1);//转换为字节数据，编码
        username = new String(usernameBytes, StandardCharsets.UTF_8);//将字节数组转换为字符串，解码
        //String newUsername = CharsetsUtil.getChinese(username);自定义的一个工具类，用于解决get方式的乱码问题
        System.out.println(username);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
