package com.meng.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet("/demo4")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        System.out.println(method);
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL);
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        System.out.println("===========");
        String queryString = req.getQueryString();
        System.out.println(queryString);
        String[] split = queryString.split("&");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split("=");
            hashMap.put(split1[0], split1[1]);
        }
        System.out.println(hashMap);
        System.out.println(hashMap.get("name"));

        String header = req.getHeader("User-Agent");
        System.out.println(header);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        System.out.println(reader.readLine());
    }
}
