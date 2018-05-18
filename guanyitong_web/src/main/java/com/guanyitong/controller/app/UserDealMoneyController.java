package com.guanyitong.controller.app;

import com.guanyitong.model.UserDealMoney;
import com.guanyitong.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.Date;

@Controller
@RequestMapping("/userDealMoney")
//用户出借记录
public class UserDealMoneyController {
    @Autowired
    private UserDealService userDealService;

    /**
     * 用户出借（添加）
     * @param userDealMoney
     * @param SYMoney  产品剩余可出借金额
     * @return
     */
    @RequestMapping("/insertUserDealMoney")
    @ResponseBody
    public JsonResult insertUserDealMoney(UserDealMoney userDealMoney,Integer SYMoney){
        JsonResult result = new JsonResult();
        try{
            userDealMoney.setCreateTime(new Date());
            if(SYMoney!=null && (SYMoney - userDealMoney.getDealMoney()>=0)){
                Boolean b = userDealService.insertUserDealMoney(userDealMoney, SYMoney - userDealMoney.getDealMoney());//用户出借，产品的出借金额减少
                if(b==true){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("出借成功");
                }
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("可出借金额不足");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("出借失败");
        }
        return result;
    }
}
