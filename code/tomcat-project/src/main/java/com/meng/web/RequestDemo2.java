package com.meng.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/demo5")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        String method = request.getMethod();
        System.out.println(method);

        String username = request.getParameter("username");
        System.out.println(username);
        byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        username = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(username);

        String password = request.getParameter("password");
        System.out.println(password);
        byte[] bytes1 = password.getBytes(StandardCharsets.ISO_8859_1);
        password = new String(bytes1, StandardCharsets.UTF_8);
        System.out.println(password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
