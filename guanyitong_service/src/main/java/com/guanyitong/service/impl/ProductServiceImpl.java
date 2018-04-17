package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.AccountManagerDao;
import com.guanyitong.mapper.ProductDao;
import com.guanyitong.mapper.UserDealDao;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao  productDao;
    @Autowired
    private UserDealDao userDealDao;
    @Autowired
    private AccountManagerDao accountManagerDao;

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

    /**
     * 出借用户统计(分页，条件)
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserProductInfoVo> selectAllUserDeal(Map map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserProductInfoVo> userProductInfoVos = productDao.selectAllUserDeal(map);
        PageInfo<UserProductInfoVo> pageInfo = new PageInfo<UserProductInfoVo>(userProductInfoVos);
        return pageInfo;
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
     * @return
     */
    @Override
    public PageInfo<Product> selectAllProducts(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productDao.selectAllProducts();
        PageInfo<Product> productPageInfo = new PageInfo<Product>(products);
        return productPageInfo;
    }

    /**
     * 查询标种的总数
     * @return
     */
    @Override
    public Integer selectProductCount() {
        return productDao.selectProductCount();
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

    /**
     * 分页查询借款列表总数量（条件查询）
     * @param map
     * @return
     */
    @Override
    public Integer selectBorrowInfoCount(Map map) {
        return productDao.selectBorrowInfoCount(map);
    }

    /**
     * 查询所有的投标，监听器监听，是否开始投标
     * @return
     */
    @Override
    public List<UserProductInfoVo> selectAllBorrowInfo() {
        return productDao.selectAllBorrowInfo();
    }

    /**
     * 根据id查询借款明细
     * @param id
     * @return
     */
    @Override
    public UserProductInfoVo selectBorrowInfoById(Long id) {
        return productDao.selectBorrowInfoById(id);
    }

    /**
     * 修改投标信息
     * @param productInfo
     * @return
     */
    @Transactional
    @Override
    public Integer updateProductInfo(ProductInfo productInfo) {
        return productDao.updateProductInfo(productInfo);
    }

    /**
     * 标，7上架，8下架，9放弃（退款给出借用户）
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Boolean updateStatus(Map map) {

        if((Integer)map.get("status")!=9){
            Integer i = productDao.updateStatus(map);
            return  i>0;
        }else{
            Integer i=0;
            Integer j=0;
            Integer k=0;
            i = productDao.updateStatus(map);
            //查询所有此表下的出借用户出借情况
            List<UserDealMoney> userDealMonies = userDealDao.selectUserDealByProductInfoId((Long) map.get("id"));
            if(userDealMonies!=null && userDealMonies.size()>0){
                for(UserDealMoney userDealMoney : userDealMonies){
                    //给每个用户退款
                    Map backMoneyMap = new HashMap();
                    backMoneyMap.put("dealMoney",userDealMoney.getDealMoney());
                    backMoneyMap.put("userId",userDealMoney.getUserId());
                    j += accountManagerDao.backMoney(backMoneyMap);
                }
                //修改状态
                Map updateStatusMap = new HashMap();
                updateStatusMap.put("status",0);
                updateStatusMap.put("productInfoId",map.get("id"));
                k =  userDealDao.updateUserDealMoneyStatus(updateStatusMap);
            }
            return i>0 && k==userDealMonies.size() && j==userDealMonies.size();//几个出借用户就修改几个状态
        }
    }
}
