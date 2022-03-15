package com.meng.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 设置字符数据的响应体
 */
@WebServlet("/resp3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、设置响应数据格式以及字符集!!!!!!!!!!否则会乱码
        resp.setContentType("text/html;charset=utf-8");
        //2、获取字符输出流
//        resp.setHeader("content-type", "text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>你好<h1>");
        //细节：1. 输出流不需要关闭，会随着response对象销毁，由服务器关闭
        //     2. 中文数据乱码：原因通过Response获取的字符字符输出流默认编码：ISO_8859_1
        //         resp.setContentType("text/html;charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
