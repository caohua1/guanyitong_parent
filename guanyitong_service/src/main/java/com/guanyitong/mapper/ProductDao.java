package com.guanyitong.mapper;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.vo.UserProductInfoVo;

import java.util.List;
import java.util.Map;
public interface ProductDao {
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
    public List<Product> selectAllProducts();

    /**
     * 查询总数
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
    public List<ProductInfo> selectAllProductinfo(ProductInfo productInfo);

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
    public List<UserProductInfoVo> selectUserProductinfo(Long productInfoId);

    /**
     * 出借用户统计(分页，条件)
     * @param map
     * @return
     */
    public List<UserProductInfoVo> selectAllUserDeal(Map map);


    //==============================================借款流程

    /**
     * 查询借款列表（分页，条件查询）
     * @param map
     * @return
     */
    public List<UserProductInfoVo> selectBorrowInfo(Map map);

    /**
     * 查询借款列表总数量（分页，条件查询）
     * @param map
     * @return
     */
    public Integer selectBorrowInfoCount(Map map);

    /**
     * 查询所有的投标，定时器监听，是否开始筹钱
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
    public Integer updateStatus(Map map);

    /**
     * 查询一条数据（根据borrowMoneyUserId 和 status）
     * @param map
     * @return
     */
    public ProductInfo selectProductInfoByStAndBUId(Map map);

    /**
     * 根据标Id和userId查询出回款方式
     * @param
     * @return
     */
    public List<ProductInfo> selectReceivableWay(Map ReceivableWayMap);
}
