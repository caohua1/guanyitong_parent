package com.guanyitong.controller.financialManagement;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserBankcard;
import com.guanyitong.service.UserBankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/BankCardManagementController")
public class BankCardManagementController {

    @Autowired
    private UserBankcardService userBankcardService;
    /**
     *展示银行卡信息
     */
    @RequestMapping("/selectUserBankcard")
    @ResponseBody
    public JsonResult selectUserBankcard(Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<UserBankcard> userBankcardPageInfo = userBankcardService.selectUserBankcardDao(pageNum, pageSize);
            System.out.println("查询银行卡信息返回报文-----"+userBankcardPageInfo);
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
     * 绑定用户银行卡
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
     * 模糊查询（按条件查找）
     */
    @RequestMapping("/selectByUserBankcard")
    @ResponseBody
    public JsonResult selectByUserBankcard(String userName,String realName,String firstDate,String lastDate){
        JsonResult result = new JsonResult();
        try{
            Map<Object, Object> conditionMap = new HashMap<Object, Object>();
            System.out.println("用户编号"+userName+"-----用户真实姓名"+realName+"-----起始时间"+firstDate+"------结束时间"+lastDate);
            conditionMap.put("userName",userName);
            conditionMap.put("realName",realName);
            conditionMap.put("firstDate",convertTime(firstDate));
            conditionMap.put("lastDate",convertTime(lastDate));
            UserBankcard userBankcard = userBankcardService.selectByUserBankcard(conditionMap);
            System.out.println("按条件查找返回报文-----"+userBankcard);
            result.setData(userBankcard);
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
     * 转换时间类型
     * @return
     */
    public Data convertTime(String date)  {
        //获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Data dateTime = null;
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            dateTime = (Data) sf.parse(date);
            System.out.println("转换时间格式------"+dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
}
