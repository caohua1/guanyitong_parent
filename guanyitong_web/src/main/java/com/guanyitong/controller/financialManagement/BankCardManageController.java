package com.guanyitong.controller.financialManagement;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserBankcard;
import com.guanyitong.service.UserBankcardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/BankCardManagementr")
public class BankCardManageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserBankcardService userBankcardService;
    /**
     * 展示借款人银行卡信息
     */
    @RequestMapping("/selectUserBankcard")
    @ResponseBody
    public JsonResult selectUserBankcard(Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<UserBankcard> userBankcardPageInfo = userBankcardService.selectUserBankcardDao(pageNum, pageSize);
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
     * 绑定借款人用户银行卡
     */
    @RequestMapping("/addUserBankcard")
    @ResponseBody
    public JsonResult addUserBankcard(UserBankcard userBankcard){
        JsonResult result = new JsonResult();
        try{
            int i = userBankcardService.insertUserBankcardDao(userBankcard);
            if(i>0){
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
     * 模糊查询
     */
    @RequestMapping("/selectByUserBankcard")
    @ResponseBody
    public JsonResult selectByUserBankcard(@RequestParam(required=false)UserBankcard userBankcard,  @RequestParam(required=false)Date firstDate,  @RequestParam(required=false)Date lastDate){
        JsonResult result = new JsonResult();
        try{
            Map<Object, Object> conditionMap = new HashMap<Object, Object>();
            if(userBankcard!=null){
                if(userBankcard.getUserName()!=null && !("").equals(userBankcard.getUserName())){
                    conditionMap.put("userName",userBankcard.getUserName());
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
            UserBankcard userBankcard1 = userBankcardService.selectByUserBankcard(conditionMap);
            result.setData(userBankcard1);
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
     * 根据userName进行条件查询
     */
    @RequestMapping("/selectByIdUserBankcard")
    @ResponseBody
    public JsonResult selectByIdUserBankcard(String userName){
        JsonResult result = new JsonResult();
        try{
            Map<Object, Object> userNameMap = new HashMap<Object, Object>();
            userNameMap.put("userName",userName);
            UserBankcard selectByUserName = userBankcardService.seelctByUserName(userNameMap);
            result.setData(selectByUserName);
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
