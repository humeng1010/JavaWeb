package com.brand.service;

import com.brand.pojo.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void addBrand(Brand brand);
    void updateBrand(Brand brand);

}
