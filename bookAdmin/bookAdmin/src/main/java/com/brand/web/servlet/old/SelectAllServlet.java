package com.brand.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.brand.pojo.Brand;
import com.brand.service.BrandService;
import com.brand.service.impl.BrandServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
//@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
