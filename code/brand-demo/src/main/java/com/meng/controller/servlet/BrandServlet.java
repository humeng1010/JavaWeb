package com.meng.controller.servlet;

import com.alibaba.fastjson.JSON;
import com.meng.pojo.Brand;
import com.meng.service.BrandService;
import com.meng.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/brands")
public class BrandServlet extends HttpServlet {
    private final BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        //把数据转为json
        String jsonString = JSON.toJSONString(brands);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
