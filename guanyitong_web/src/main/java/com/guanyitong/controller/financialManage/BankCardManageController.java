package com.guanyitong.controller.financialManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserBankcard;
import com.guanyitong.service.UserBankcardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//借款人银行卡管理
@Controller
@RequestMapping("/BankCardManagementr")
public class BankCardManageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserBankcardService userBankcardService;

    /**
     * 绑定借款人用户银行卡（添加数据）
     */
    @RequestMapping("/addUserBankcard")
    @ResponseBody
    public JsonResult addUserBankcard(UserBankcard userBankcard){
        System.out.println(userBankcard.getBorrowMoneyUserId()+"----"+userBankcard.getRealName()+"----"+
                userBankcard.getBankName()+"---"+userBankcard.getIDCardNumber()+"-----"+userBankcard.getOpenAccountRegion()+"---"+userBankcard.getCardNo()+"----"+userBankcard.getYN());
        JsonResult result = new JsonResult();
        try{
            int i = userBankcardService.insertUserBankcardDao(userBankcard);
            if(i>0){
                System.out.println("返回数据");
                result.setData(i);
                result.setState(JsonResult.SUCCESS);
                result.setMessage("返回数据成功");
            }else {
                result.setState(JsonResult.ERROR);
                result.setMessage("返回数据失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
    /**
     * 条件查询（借款人银行卡信息）
     */
    @RequestMapping("/selectByUserBankcard")
    @ResponseBody
    public JsonResult selectByUserBankcard(Integer pageNum, Integer pageSize,UserBankcard userBankcard,  @RequestParam(required=false)String first,  @RequestParam(required=false)String last){
        JsonResult result = new JsonResult();
        DateAndTimeUtil dateAndTimeUtil = new DateAndTimeUtil();
        Date firstDate=null;
        Date lastDate =null;
        if(first!=null&&first!=""){
            firstDate = dateAndTimeUtil.convert(first);
        }
        if(last!=null&&last!=""){
            lastDate = dateAndTimeUtil.convert(last);
        }
        System.out.println("字符串转换时间类型--------"+lastDate);
        try{
            Map<Object, Object> conditionMap = new HashMap<Object, Object>();
            if(userBankcard!=null){
                if(userBankcard.getUserName()!=null && !("").equals(userBankcard.getUserName())){
                    conditionMap.put("borrowMoneyUserId",userBankcard.getUserName());
                }
               if(userBankcard.getRealName()!=null && !("").equals(userBankcard.getRealName())){
                   conditionMap.put("realName",userBankcard.getRealName());
               }
               if (userBankcard.getIDCardNumber()!=null &&!("").equals(userBankcard.getIDCardNumber())){
                   conditionMap.put("IDCardNumber",userBankcard.getIDCardNumber());
               }
                if (userBankcard.getCardNo()!=null && !("").equals(userBankcard.getCardNo())){
                    conditionMap.put("cardNo",userBankcard.getCardNo());
                }
                if(firstDate !=null){
                    conditionMap.put("firstDate",firstDate);
                }
                if(lastDate !=null){
                    conditionMap.put("lastDate",lastDate);
                }
            }
            PageInfo<UserBankcard> userBankcardPageInfo = userBankcardService.selectByUserBankcard(conditionMap, pageNum, pageSize);
            result.setData(userBankcardPageInfo);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 根据主键查询详情
     * @param userId
     * @return
     */
    @RequestMapping("/selectUserBankcardById")
    public String selectUserBankcardById(String userId, Model model){
        Long id= Long.valueOf(userId);
        try{
            UserBankcard userBankcard = userBankcardService.selectUserBankcardById(id);
            if(userBankcard!=null){
                model.addAttribute("userBankcard",userBankcard);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "borrowUserBankManager/borrowUserBank_select";
    }

    /**
     * 模糊查询用户id
     * @param borrowMoneyUserId
     * @return
     */
    @RequestMapping("/selectDimId")
    @ResponseBody
    public JsonResult selectDimId(String borrowMoneyUserId){
        JsonResult result = new JsonResult();
        try{
            Long dimId= Long.valueOf(borrowMoneyUserId);
            List<UserBankcard> userBankcards = userBankcardService.selectDimId(dimId);
            result.setData(userBankcards);
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
