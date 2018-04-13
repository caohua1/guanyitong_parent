package com.guanyitong.service;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.vo.UserProductInfoVo;

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

    /**
     * 出借用户统计(分页，条件)
     * @param map
     * @return
     */
    public PageInfo<UserProductInfoVo> selectAllUserDeal(Map map,Integer pageNum,Integer pageSize);



    //===========================================后台管理

    /**
     * 添加产品（标种）
     * @param product
     * @return
     */
    public Integer insertProduct(Product product);

    /**
     * 查询所有的产品分页
     * @return
     */
    public PageInfo<Product> selectAllProducts(Integer pageNum,Integer pageSize);

    /**
     * 查询标种的总数
     * @return
     */
    public Integer selectProductCount();

    /**
     * 根据id查询某一种产品，编辑页面，回显数据
     * @param id
     * @return
     */
    public Product selectProductById(Long id);

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    public Integer updateProduct(Product product);

    /**
     * 删除产品，根据id
     * @param id
     * @return
     */
    public Integer deleteProduct(Long id);

    //==============================================投标

    /**
     * 添加投标
     * @param productInfo
     * @return
     */
    public Integer insertProductInfo(ProductInfo productInfo);

    /**
     * 分页查询投标
     * @param productInfo
     * @return
     */
    public PageInfo<ProductInfo> selectAllProductinfo(ProductInfo productInfo,Integer pageNum,Integer pageSize);

    /**
     * 删除投标
     * @param id
     * @return
     */
    public Integer deleteProductinfo(Long id);
    /**
     * 查看投标详情，所有用户的此投标的信息
     * @param productInfoId
     * @return
     */
    public PageInfo<UserProductInfoVo> selectUserProductinfo(Long productInfoId, Integer pageNum, Integer pageSize);


    //==============================================借款流程

    /**
     * 查询借款列表（分页，条件查询）
     * @param map
     * @return
     */
    public PageInfo<UserProductInfoVo> selectBorrowInfo(Map map,Integer pageNum,Integer pageSize);

    /**
     * 查询借款列表总数量（分页，条件查询）
     * @param map
     * @return
     */
    public Integer selectBorrowInfoCount(Map map);

    /**
     *查询所有的投标，定时器监听，是否开始筹钱
     * @return
     */
    public List<UserProductInfoVo> selectAllBorrowInfo();

    /**
     * 根据id查询借款明细
     * @param id
     * @return
     */
    public UserProductInfoVo selectBorrowInfoById(Long id);
    /**
     * 修改投标信息
     * @param productInfo
     * @return
     */
    public Integer updateProductInfo(ProductInfo productInfo);

    /**
     * 产品上架，下架，放弃（退款给用户）
     * @param map
     * @return
     */
    public Boolean updateStatus(Map map);
}
