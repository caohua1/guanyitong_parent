package com.guanyitong.service.impl;

import com.guanyitong.mapper.ProductDao;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao  productDao;


    /**
     * 查询产品
     * @param map
     * @return
     */
    @Override
    public List<Product> selectProduct(Map map) {
        return productDao.selectProduct(map);
    }

    /**
     * 查询产品的详细信息
     * @param map
     * @return
     */
    @Override
    public List<ProductInfo> selectProductInfo(Map map) {
        return productDao.selectProductInfo(map);
    }
}
