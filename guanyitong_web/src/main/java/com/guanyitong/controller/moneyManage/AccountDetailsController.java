package com.guanyitong.controller.moneyManage;

import com.guanyitong.model.AccountDetails;
import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.MoneyManageVo;
import com.guanyitong.service.AccountDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/AccountDetails")
//出借人账户明细
public class AccountDetailsController {
    @Autowired
    private AccountDetailsService accountDetailsService;


    /**
     * 账户明细页面跳转
     * @param model
     * @param moneyManageVo
     * @return
     */
    @RequestMapping("/skipDetails")
    public String skipDetails(Model model, MoneyManageVo moneyManageVo){
        model.addAttribute("moneyManage",moneyManageVo);
        return "";
    }


    /**
     * 查询出借人账户明细（ajxa请求接口）
     * @param userId(出借人id)
     * @param //startTime（查询起始时间）
     * @param //endTime（查询结束时间）
     * @param //PmenType（收支类型 0代表收入，1代表支出）
     * @param //type（类型 C 代表充值类型，H 代表回款类型，J 代表出借类型）
     * @return
     */
    @RequestMapping("/checkDetails")
    @ResponseBody
    public JsonResult checkDetails(Long userId,AccountDetails accountDetails){
        JsonResult result = new JsonResult();
        HashMap<String, Object> accountDetailsMap = new HashMap<String, Object>();
        HashMap<Object, Object> conditionMap = new HashMap<Object, Object>();
        conditionMap.put("userId",userId);
        try{
            //判断是否是条件查询
            if(0==accountDetails.getCondition()) {
                if (accountDetails.getStartTime() != null || accountDetails.getEndTime() != null) {
                    conditionMap.put("startTime", accountDetails.getStartTime());
                    conditionMap.put("endTime", accountDetails.getEndTime());
                }
                //查询出借人充值记录
                List<RechargeMoney> rechargeMoneyList = accountDetailsService.selectRechargeList(conditionMap);
                //查询出借人出借记录
                List<UserDealMoney> userDealMoneyList = accountDetailsService.selectUserDealMoneyList(conditionMap);
                //查询出借人回款记录和收益记录
                List<UserDealBackMoneyRecord> moneyRecordList = accountDetailsService.selectReturnedEarningsMoney(conditionMap);
                //判断是否有收支类型的选择（收支类型 0代表收入，1代表支出）
                if ("0".equals(accountDetails.getPmenType())) {
                    //PmenType==0表示收入类型
                    accountDetailsMap.put("CH", rechargeMoneyList);
                    accountDetailsMap.put("HS", moneyRecordList);
                } else if ("1".equals(accountDetails.getPmenType())){
                    //PmenType==1表示支出类型
                    accountDetailsMap.put("ZC", userDealMoneyList);
                }
                //判断是否有类型的选择（C 代表充值类型，H 代表回款类型，J 代表出借类型）
                if ("C".equals(accountDetails.getType())) {
                    //表示选择的是充值类型
                    accountDetailsMap.put("CH", rechargeMoneyList);
                } else if ("H".equals(accountDetails.getType())) {
                    //表示选择的是回款类型
                    ArrayList<String> BjMoney = new ArrayList<String>();
                    for (UserDealBackMoneyRecord returnedMoney : moneyRecordList
                            ) {
                        BjMoney.add(returnedMoney.getBj());
                    }
                    accountDetailsMap.put("HS", BjMoney);
                } else if ("J".equals(accountDetails.getType())){
                    //表示选择的是出借类型
                    accountDetailsMap.put("ZC", userDealMoneyList);
                }
            }else {
                //查询出借人充值记录
                accountDetailsMap.put("CH", accountDetailsService.selectRechargeList(conditionMap));
                //查询出借人出借记录
                accountDetailsMap.put("ZC",accountDetailsService.selectUserDealMoneyList(conditionMap));
                //查询出借人回款记录和收益记录
                accountDetailsMap.put("HS", accountDetailsService.selectReturnedEarningsMoney(conditionMap));
            }
            result.setData(accountDetailsMap);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
