package com.guanyitong.controller.app;
import com.guanyitong.model.*;
import com.guanyitong.service.AccountManagerService;
import com.guanyitong.service.UserBackMoneyRecordService;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.CheckBankCard;
import util.CheckIdCard;
import util.FinalData;
import util.JsonResult;

import java.util.*;

/**
 * 开户
 */
@Controller
@RequestMapping("/api")
public class AccountManagerController {
    @Autowired
    private AccountManagerService accountManagerService;
    @Autowired
    private WithdrawMoneyService withdrawMoneyService;
    @Autowired
    private UserBackMoneyRecordService userBackMoneyRecordService;

    /**
     * 验证身份证号格式是否符合
     * @param idCard 身份证号
     * @return
     */
    @RequestMapping(value = "/checkIdCard")
    @ResponseBody
    public JsonResult checkIdCard(String idCard){
        JsonResult result = new JsonResult();
        try{
            String tipInfo = CheckIdCard.checkIdCard(idCard);
            result.setState(JsonResult.SUCCESS);
            result.setMessage(tipInfo);
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("网络出现问题");
        }
        return result;
    }

    /**
     * 验证银行卡格式是否正确
     * @param bankNum 银行卡号
     * @return
     */
    @RequestMapping(value = "/checkBankCard")
    @ResponseBody
    public JsonResult checkBankCard(String bankNum){
        JsonResult result = new JsonResult();
        try{
            boolean b = CheckBankCard.checkBankCard(bankNum);//调用验证工具类
            if(b==false){
                result.setState(JsonResult.ERROR);
                result.setMessage("银行卡号格式不符合规定");
            }else{
                result.setState(JsonResult.SUCCESS);
                result.setMessage("银行卡格式正确");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("网络出现问题");
        }
        return result;
    }
    /**
     * 用户绑定银行卡（开户）
     * @param accountManager
     * @return
     */
    @RequestMapping(value = "/openAccount",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult openAccount(AccountManager accountManager, UserPayInfo userPayInfo){
        JsonResult result = new JsonResult();
        try{

            //1.实名认证（真实姓名，是否与银行卡号对应）
            //2.检验此银行卡是否已经绑定
            accountManager.setCreateTime(new Date());
            AccountManager accountManager1 = accountManagerService.selectBank(accountManager);
            if(accountManager1==null){//查询是否绑定过此银行卡
                int i = accountManagerService.openAccount(accountManager,userPayInfo);
                if(i>0){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("开户（绑定银行卡）成功");
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("银行卡绑定失败");
                }
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("此银行卡已经绑定了");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("开户失败");
        }
        return result;
    }
    /**
     * 查询当前用户的（出借）交易记录情况
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectUserDealInfo",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectUserDealInfo(Long userId){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            map.put("userId",userId);
            List<UserDealMoney> userDealMonies = accountManagerService.selectUserOutInfo(map);
            if(userDealMonies!=null){
                    for(UserDealMoney userDealMoney : userDealMonies){
                        ProductInfo productInfo = accountManagerService.selectProductInfo(userDealMoney.getProductInfoId());
                        userDealMoney.setProductInfo(productInfo);//把产品信息set进去
                }
                result.setData(userDealMonies);
                result.setState(JsonResult.SUCCESS);
                result.setMessage("返回数据成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }


    /**
     * 查询当前用户的提现记录
     * @param  userId
     * @param  status (0失败，1已提现,转账中，2审核中)
     * @return
     */
    @RequestMapping("/selectUserWithdrawalMoney")
    @ResponseBody
    public JsonResult selectUserWithdrawalMoney(Long userId,@RequestParam(required=false)Integer status){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(userId !=null){
                map.put("userId",userId);
            }
            if(status !=null){
                map.put("status",status);
            }
            List<WithdrawalMoney> withdrawalMonies = accountManagerService.selectUserWithdrawalMoney(map);
            result.setState(JsonResult.SUCCESS);
            result.setData(withdrawalMonies);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 出借人申请提现（查看余额是否足够）
     * @param withdrawalMoney
     * @return
     */
    @RequestMapping("/withdrawal")
    @ResponseBody
    public JsonResult withdrawal(WithdrawalMoney withdrawalMoney){
       JsonResult result = new JsonResult();
       try{
           if(withdrawalMoney !=null){
               AccountManager accountManager = accountManagerService.selectUserYuE(withdrawalMoney.getUserId());
               if(accountManager !=null){
                   int txMoney = Integer.parseInt(withdrawalMoney.getTxMoney());//提现金额
                   if( accountManager.getYuE()>=txMoney){
                       withdrawalMoney.setUserType(0);//出借人 提现
                       Boolean b = withdrawMoneyService.insertWithdrawMoney(withdrawalMoney);
                       if(b==true){
                           result.setState(JsonResult.SUCCESS);
                           result.setMessage("申请提现成功");
                       }else{
                           result.setState(JsonResult.ERROR);
                           result.setMessage("申请提现失败");
                       }
                   }else{
                       result.setState(JsonResult.ERROR);
                       result.setMessage("您的余额不足");
                   }
               }
           }
       }catch(Exception e){
           e.printStackTrace();
           result.setState(JsonResult.ERROR);
           result.setMessage("操作失败");
       }
       return result;
    }

    /**
     * 查询当前用户的充值记录
     * @param userId
     * @return
     */
    @RequestMapping("/selectUserRechargeMoney")
    @ResponseBody
    public JsonResult selectUserRechargeMoney(Long userId){
        JsonResult result = new JsonResult();
        try{
            List<RechargeMoney> rechargeMonies = accountManagerService.selectUserRechargeMoney(userId);
            result.setState(JsonResult.SUCCESS);
            result.setData(rechargeMonies);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询某用户的回款记录
     * @param userId
     * @return
     */
    @RequestMapping("/userBackMoneyRecord")
    @ResponseBody
    public JsonResult userBackMoneyRecord(Long userId){
        JsonResult result = new JsonResult();
        try{
            List<UserDealBackMoneyRecord> userDealBackMoneyRecords = userBackMoneyRecordService.selectUserBackMoneyRecord(userId);
            result.setState(JsonResult.SUCCESS);
            result.setData(userDealBackMoneyRecords);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询我的宝箱
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectMyTreasure",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectMyTreasure(Long userId){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            map.put("userId",userId);
            List<UserTreasure> userTreasures = accountManagerService.selectMyTreasure(map);
            if(userTreasures!=null){
                Map myTreasure = new HashMap();
                List HB = new ArrayList();
                List XJQ = new ArrayList();
                List TYJ = new ArrayList();
                List JXQ = new ArrayList();
                List THQ = new ArrayList();
                for(UserTreasure u : userTreasures){
                    map.put("treasureId",u.getTreasureId());
                    Treasure treasure = accountManagerService.selectTreasure(map);
                    u.setTreasure(treasure);
                    if(treasure !=null){
                        if(treasure.getType()==0){//红包
                            HB.add(u);
                        }else if(treasure.getType()==1){//现金券
                            XJQ.add(u);
                        }else if(treasure.getType()==2){//体验金
                            TYJ.add(u);
                        }else if(treasure.getType()==3){//加息券
                            JXQ.add(u);
                        }else{       //提货券
                            THQ.add(u);
                        }
                    }
                }
                int HB_count=0;
                int XJQ_count=0;
                int TYJ_count=0;
                int JXQ_count=0;
                int THQ_count=0;
                if(HB.size()>0){
                    for(int i=0;i<HB.size();i++){
                        UserTreasure userTreasur = (UserTreasure) HB.get(i);
                        HB_count+= userTreasur.getNum();
                    }
                }
                if(XJQ.size()>0){
                    for(int i=0;i<XJQ.size();i++){
                        UserTreasure userTreasur = (UserTreasure) XJQ.get(i);
                        XJQ_count+= userTreasur.getNum();
                    }
                }
                if(TYJ.size()>0){
                    for(int i=0;i<TYJ.size();i++){
                        UserTreasure userTreasur = (UserTreasure) TYJ.get(i);
                        TYJ_count+= userTreasur.getNum();
                    }
                }
                if(JXQ.size()>0){
                    for(int i=0;i<JXQ.size();i++){
                        UserTreasure userTreasur = (UserTreasure) JXQ.get(i);
                        JXQ_count+= userTreasur.getNum();
                    }
                }
                if(THQ.size()>0){
                    for(int i=0;i<THQ.size();i++){
                        UserTreasure userTreasur = (UserTreasure) THQ.get(i);
                        THQ_count+= userTreasur.getNum();
                    }
                }
                myTreasure.put(FinalData.HB,HB);
                myTreasure.put("HB_count",HB_count);
                myTreasure.put(FinalData.XJQ,XJQ);
                myTreasure.put("XJQ_count",XJQ_count);
                myTreasure.put(FinalData.TYJ,TYJ);
                myTreasure.put("TYJ_count",TYJ_count);
                myTreasure.put(FinalData.JXQ,JXQ);
                myTreasure.put("JXQ_count",JXQ_count);
                myTreasure.put(FinalData.THQ,THQ);
                myTreasure.put("THQ_count",THQ_count);
                result.setState(JsonResult.SUCCESS);
                result.setData(myTreasure);
                result.setMessage("返回数据成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
