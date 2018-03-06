package com.guanyitong.mapper;

import com.guanyitong.model.*;

import java.util.List;
import java.util.Map;

public interface AccountManagerDao {
    /**
     * 用户开户
     * @param accountManager
     * @return
     */
    public int openAccount(AccountManager accountManager);

    /**
     * 用户绑定银行卡，设置平台支付密码
     * @param userPayInfo
     * @return
     */
    public int insertPayInfo(UserPayInfo userPayInfo);

    /**
     * 查询此银行卡是否已经注册过
     * @param accountManager
     * @return
     */
    public AccountManager selectBank(AccountManager accountManager);

    /**
     * 根据id查询某一个出借记录
     * @param id
     * @return
     */
    public UserDealMoney selectDealMoneyById(Long id);
    /**
     * 查询当前用户的出借情况
     * @param map
     * @return
     */
    public List<UserDealMoney> selectUserOutInfo(Map map);

    /**
     * 查询当前用户回款记录
     * @param map
     * @return
     */
    public List<BackMoney> selectUserBackMoney(Map map);

    /**
     * 查询当前用户的提现记录
     * @param map
     * @return
     */
    public List<WithdrawalMoney> selectUserWithdrawalMoney(Map map);

    /**
     * 查询当前用户的充值记录
     * @param userId
     * @return
     */
    public List<RechargeMoney> selectUserRechargeMoney(Long userId);

    /**
     * 查询某个产品的具体信息
     * @param productInfoId
     * @return
     */
    public ProductInfo selectProductInfo(Long productInfoId);

    /**
     * 查询我的宝箱
     * @param map
     * @return
     */
    public List<UserTreasure> selectMyTreasure(Map map);

    /**
     * 查询宝箱详情
     * @param map
     * @return
     */
    public Treasure selectTreasure(Map map);

    /**
     * 查询宝箱详情
     * @param map
     * @return
     */
    public List<Treasure> selectTreasures(Map map);

    /**
     * 添加用户宝箱
     * @param userTreasure
     * @return
     */
    public int insertUserTreasure(UserTreasure userTreasure);

    /**
     *修改宝箱的库存
     * @param map
     * @return
     */
    public int updateInventory(Map map);

}
