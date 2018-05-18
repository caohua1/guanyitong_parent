package com.guanyitong.service.impl;
import com.guanyitong.mapper.AccountManagerDao;
import com.guanyitong.mapper.UserDAO;
import com.guanyitong.model.*;
import com.guanyitong.service.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.FinalData;
import util.MD5Util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AccountManagerServiceImpl  implements AccountManagerService {
    @Autowired
    private AccountManagerDao accountManagerDao;
    @Autowired
    private UserDAO userDAO;
    /**
     * 用户开户(可以绑定多个银行卡),送冠豆
     * @param accountManager
     * @return
     */
    @Transactional
    @Override
    public int openAccount(AccountManager accountManager,UserPayInfo userPayInfo) {
        int i = 0;
        int k = 0;
        UserGuanDou userGuanDou1 = new UserGuanDou();
        userGuanDou1.setUserId(accountManager.getUserId());
        userGuanDou1.setGuanDou(20);
        userGuanDou1.setCreatTime(new Date());
        userGuanDou1.setContent(FinalData.OPEN_BANK_GUANDOU);
       if(userPayInfo.getPayPassword()!=null){//不为空的话就是还没绑定过其他银行卡,需要设置支付密码
           int j =  accountManagerDao.openAccount(accountManager);
           i = userDAO.insertGuanDou(userGuanDou1);
           String s = MD5Util.MD5(userPayInfo.getPayPassword());//对平台支付密码进行加密
           userPayInfo.setUserId(accountManager.getUserId());
           userPayInfo.setPayPassword(s);
           k = accountManagerDao.insertPayInfo(userPayInfo);//添加支付密码表
           return (i>0&&j>0&&k>0)?1:0;
       }else{
           return 0;
       }
    }
    /**
     * 查询此银行卡是否已经绑定过
     * @param accountManager
     * @return
     */
    @Override
    public AccountManager selectBank(AccountManager accountManager) {
        return accountManagerDao.selectBank(accountManager);
    }

    /**
     * 查看出借用户的余额
     * @param userId
     * @return
     */
    @Override
    public AccountManager selectUserYuE(Long userId) {
        return accountManagerDao.selectUserYuE(userId);
    }

    /**
     * 根据id查询某一个出借记录
     * @param id
     * @return
     */
    @Override
    public UserDealMoney selectDealMoneyById(Long id) {
        return accountManagerDao.selectDealMoneyById(id);
    }

    /**
     * 查询用户的出借情况
     * @param map
     * @return
     */
    @Override
    public List<UserDealMoney> selectUserOutInfo(Map map) {
        return accountManagerDao.selectUserOutInfo(map);
    }

    /**
     * 查询当前用户的提现记录
     * @param map
     * @return
     */
    @Override
    public List<WithdrawalMoney> selectUserWithdrawalMoney(Map map) {
        return accountManagerDao.selectUserWithdrawalMoney(map);
    }

    /**
     * 查询当前用户的充值记录
     * @param userId
     * @return
     */
    @Override
    public List<RechargeMoney> selectUserRechargeMoney(Long userId) {
        return accountManagerDao.selectUserRechargeMoney(userId);
    }

    /**
     * 查询某个产品的信息
     * @param productInfoId
     * @return
     */
    @Override
    public ProductInfo selectProductInfo(Long productInfoId) {
        return accountManagerDao.selectProductInfo(productInfoId);
    }

    /**
     * 查询我的宝箱
     * @param map
     * @return
     */
    @Override
    public List<UserTreasure> selectMyTreasure(Map map) {
        return accountManagerDao.selectMyTreasure(map);
    }

    /**
     * 查询宝箱的详情
     * @param map
     * @return
     */
    @Override
    public Treasure selectTreasure(Map map) {
        return accountManagerDao.selectTreasure(map);
    }

    /**
     * 查询宝箱的详情(展示到首页的冠豆商城)
     * @param map
     * @return
     */
    @Override
    public List<Treasure> selectTreasures(Map map) {
        return accountManagerDao.selectTreasures(map);
    }

    /**
     * 兑换，添加用户的宝箱，同时宝箱的库存减少，用户的冠豆会减少
     * @param userTreasure
     * @return
     */
    @Transactional
    @Override
    public int insertUserTreasure(UserTreasure userTreasure,Treasure treasure) {
        int i = accountManagerDao.insertUserTreasure(userTreasure);
        Map map = new HashMap();
        UserGuanDou userGuanDou = new UserGuanDou();
        if(treasure.getType()==0){
            userGuanDou.setContent(FinalData.EXCHANGE_HB_GUSNDOU);
        }else if(treasure.getType()==3){
            userGuanDou.setContent(FinalData.EXCHANGE_JXQ_GUANDOU);
        }
        userGuanDou.setGuanDou((0-treasure.getGuandou())*userTreasure.getNum());//用户的冠豆数为负数，表示是消费
        userGuanDou.setUserId(userTreasure.getUserId());
        userGuanDou.setCreatTime(new Date());
        int i1 = userDAO.insertGuanDou(userGuanDou);
        map.put("id",treasure.getId());
        map.put("inventory",treasure.getInventory()-userTreasure.getNum());
        map.put("saleNum",treasure.getSaleNum()+userTreasure.getNum());
        int i2 = accountManagerDao.updateInventory(map);
        return (i>0&&i1>0&&i2>0)?1:0;
    }


}
