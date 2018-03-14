package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.ProductDao;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //=======================================================后台管理
    /**
     * 添加产品（标种）
     * @param product
     * @return
     */
    @Transactional
    @Override
    public Integer insertProduct(Product product) {
        Integer i = productDao.insertProduct(product);
        return i;
    }

    /**
     * 查询所有的产品分页
     * @param product
     * @return
     */
    @Override
    public PageInfo<Product> selectAllProducts(Product product, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productDao.selectAllProducts(product);
        PageInfo<Product> productPageInfo = new PageInfo<Product>(products);
        return productPageInfo;
    }

    /**
     * 根据id查询某一种产品，编辑页面，回显数据
     * @param id
     * @return
     */
    @Override
    public Product selectProductById(Long id) {
        Product product = productDao.selectProductById(id);
        return product;
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    @Transactional
    @Override
    public Integer updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    /**
     * 删除产品，根据id
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Integer deleteProduct(Long id) {
        return productDao.deleteProduct(id);
    }

    //======================================================================投标

    /**
     *添加投标
     * @param productInfo
     * @return
     */
    @Transactional
    @Override
    public Integer insertProductInfo(ProductInfo productInfo) {
        return  productDao.insertProductInfo(productInfo);
    }

    /**
     * 分页查询所有的投标
     * @param productInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ProductInfo> selectAllProductinfo(ProductInfo productInfo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ProductInfo> productInfos = productDao.selectAllProductinfo(productInfo);
        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<ProductInfo>(productInfos);
        return productInfoPageInfo;
    }

    /**
     * 删除投标
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Integer deleteProductinfo(Long id) {
        return productDao.deleteProductinfo(id);
    }

    /**
     * 查看投标详情，所有用户的此投标的信息
     * @param productInfoId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserProductInfoVo> selectUserProductinfo(Long productInfoId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserProductInfoVo> userProductInfoVos = productDao.selectUserProductinfo(productInfoId);
        PageInfo<UserProductInfoVo> productInfoPageInfo = new PageInfo<UserProductInfoVo>(userProductInfoVos);
        return productInfoPageInfo;
    }

    /**
     * 分页查询借款列表（条件查询）
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserProductInfoVo> selectBorrowInfo(Map map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserProductInfoVo> userProductInfoVos = productDao.selectBorrowInfo(map);
        PageInfo<UserProductInfoVo> productInfoVoPageInfo = new PageInfo<UserProductInfoVo>(userProductInfoVos);
        return productInfoVoPageInfo;
    }
}
