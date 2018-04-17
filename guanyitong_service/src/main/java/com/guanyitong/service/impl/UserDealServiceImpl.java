package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.ProductDao;
import com.guanyitong.mapper.UserDealDao;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.UserDealMoneyVo;
import com.guanyitong.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserDealServiceImpl implements UserDealService {
    @Autowired
    private UserDealDao userDealDao;
    @Autowired
    private ProductDao productDao;

    /**
     * 用户出借（添加）
     * @param userDealMoney
     * @return
     */
    @Transactional
    @Override
    public Boolean insertUserDealMoney(UserDealMoney userDealMoney,Integer SYMoney) {
        Integer i = userDealDao.insertUserDealMoney(userDealMoney);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setUpdateTime(new Date());
        productInfo.setSYMoney(SYMoney);
        productInfo.setId(userDealMoney.getProductInfoId());
        Integer j = productDao.updateProductInfo(productInfo);//产品的可出借金额减少
        if(i>0 && j>0 && SYMoney ==0){//如果剩余可出借金额为0,修改标的状态为  status = 5 筹集完成（待提现）
            Map map = new HashMap();
            map.put("id",userDealMoney.getProductInfoId());
            map.put("status",5);
            Integer k = productDao.updateStatus(map);
            return i>0 && j>0 && k>0;
        }else{
            return i>0 && j>0;
        }

    }

    /**
     * (分页)查询所有用户的出借情况
     * @param map
     * @return
     */
    @Override
    public PageInfo<UserDealMoneyVo> selectAllUserDeal(Map map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserDealMoneyVo> userDealMoneyVos = userDealDao.selectAllUserDeal(map);
        PageInfo<UserDealMoneyVo> pageInfo = new PageInfo<UserDealMoneyVo>(userDealMoneyVos);
        return pageInfo;
    }

    /**
     * 查询所有用户的出借数量
     * @param map
     * @return
     */
    @Override
    public Integer selectAllUserDealCount(Map map) {
        return userDealDao.selectAllUserDealCount(map);
    }

    /**
     * 统计某标的出借人数
     * @param productInfoId
     * @return
     */
    @Override
    public Integer selectCountByProductInfoId(Long productInfoId) {
        return userDealDao.selectCountByProductInfoId(productInfoId);
    }
}
