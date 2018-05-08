package com.guanyitong.controller.financialManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.BorrowMoneyUser;
import com.guanyitong.model.UserBankcard;
import com.guanyitong.service.BorrowMoneyUserService;
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

import java.text.SimpleDateFormat;
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

    @Autowired
    private BorrowMoneyUserService borrowMoneyUserService;
    /**
     * 绑定借款人用户银行卡（添加数据）
     */
    @RequestMapping("/addUserBankcard")
    @ResponseBody
    public JsonResult addUserBankcard(UserBankcard userBankcard){
        JsonResult result = new JsonResult();
        try{
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = sdf.parse(sdf.format(date));
            userBankcard.setSubmitTime(parse);
            int i = userBankcardService.insertUserBankcardDao(userBankcard);
            if(i>0){
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
    public JsonResult selectByUserBankcard(Integer pageNum, Integer pageSize,UserBankcard userBankcard,  @RequestParam(required=false)String firstDate,  @RequestParam(required=false)String lastDate){
        JsonResult result = new JsonResult();
        DateAndTimeUtil dateAndTimeUtil = new DateAndTimeUtil();
        Date firstTime=null;
        Date lastTime =null;
        if(firstDate!=null&&firstDate!=""){
            firstTime = dateAndTimeUtil.convert(firstDate);
        }
        if(lastDate!=null&&lastDate!=""){
            lastTime = dateAndTimeUtil.convert(lastDate);
        }
        try{
            Map<Object, Object> conditionMap = new HashMap<Object, Object>();
            if(userBankcard!=null){
                if(userBankcard.getBorrowMoneyUserId()!=null && !("").equals(userBankcard.getBorrowMoneyUserId())){
                    conditionMap.put("borrowMoneyUserId",userBankcard.getBorrowMoneyUserId());
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
                if(firstTime !=null){
                    conditionMap.put("firstTime",firstTime);
                }
                if(lastTime !=null){
                    conditionMap.put("lastTime",lastTime);
                }
            }
            PageInfo<UserBankcard> userBankcardPageInfo = userBankcardService.selectByUserBankcard(conditionMap, pageNum, pageSize);
            Integer count = userBankcardService.selectUserBankcardAcount(conditionMap);
            HashMap<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("pageInfo",userBankcardPageInfo);
            dataMap.put("count",count);
            result.setData(dataMap);
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
     * @param borrowMoneyUserId
     * @return
     */
    @RequestMapping("/selectUserBankcardById")
    public String selectUserBankcardById(Long borrowMoneyUserId, Model model){
        try{
            UserBankcard userBankcard = userBankcardService.selectUserBankcardById(String.valueOf(borrowMoneyUserId));
            if(userBankcard!=null){
               if(userBankcard.getRealName()==null){
                   userBankcard.setRealName("暂无数据");
               }if(userBankcard.getIDCardNumber()==null){
                    userBankcard.setIDCardNumber("暂无数据");
                }if(userBankcard.getCardNo()==null){
                    userBankcard.setCardNo("暂无数据");
                }if(userBankcard.getBankName()==null){
                    userBankcard.setBankName("暂无数据");
                }if(userBankcard.getOpenAccountRegion()==null){
                    userBankcard.setOpenAccountRegion("暂无数据");
                }if(userBankcard.getPhone()==null){
                    userBankcard.setPhone("暂无数据");
                }
                model.addAttribute("userBankcard",userBankcard);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "moneyManager/borrowUserBank_select";
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
            List<BorrowMoneyUser> userBankcards = borrowMoneyUserService.selectDimId(dimId);
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