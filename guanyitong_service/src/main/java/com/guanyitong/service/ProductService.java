package com.guanyitong.service;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import java.util.List;
import java.util.Map;
public interface ProductService {
    /**
     * 查询所有的产品
     * @return
     */
    public List<Product> selectProduct(Map map);

    /**
     * 查询某种产品的详情（不同期限的）
     * @param map
     * @return
     */
    public List<ProductInfo> selectProductInfo(Map map);
}
