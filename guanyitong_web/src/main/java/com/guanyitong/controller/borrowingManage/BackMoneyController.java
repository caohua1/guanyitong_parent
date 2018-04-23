package com.guanyitong.controller.borrowingManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.BackMoney;
import com.guanyitong.model.vo.BackMoneyManageListVo;
import com.guanyitong.service.BackMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/backMoney")
@Controller
public class BackMoneyController {
    @Autowired
    private BackMoneyService backMoneyService;

    /**
     * 财务管理模块（还款管理列表）
     * @param backMoneyManageListVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/backMoneyList")
    @ResponseBody
    public JsonResult backMoneyList(BackMoneyManageListVo backMoneyManageListVo,Integer pageNum,Integer pageSize,String startTime,String endTime){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(backMoneyManageListVo!=null){
                if(backMoneyManageListVo.getBorrowMoneyUserId()!=null && !("").equals(backMoneyManageListVo.getBorrowMoneyUserId())){
                    map.put("borrowMoneyUserId",backMoneyManageListVo.getBorrowMoneyUserId());
                }
                if(backMoneyManageListVo.getIDCardNumber()!=null && !("").equals(backMoneyManageListVo.getIDCardNumber())){
                    map.put("IDCardNumber",backMoneyManageListVo.getIDCardNumber());
                }
                if(backMoneyManageListVo.getRealName()!=null && !("").equals(backMoneyManageListVo.getRealName())){
                    map.put("realName",backMoneyManageListVo.getRealName());
                }
                if(backMoneyManageListVo.getBankName()!=null && !("").equals(backMoneyManageListVo.getBankName())){
                    map.put("bankName",backMoneyManageListVo.getBankName());
                }
                if(backMoneyManageListVo.getCardNo()!=null && !("").equals(backMoneyManageListVo.getCardNo())){
                    map.put("cardNo",backMoneyManageListVo.getCardNo());
                }
                if(backMoneyManageListVo.getBj()!=null && !("").equals(backMoneyManageListVo.getBj()) ){
                    map.put("bj",backMoneyManageListVo.getBj());
                }
            }
            if(startTime !=null && !("").equals(startTime)){
                map.put("startTime", DateAndTimeUtil.convert(startTime));
            }
            if(endTime !=null && !("").equals(endTime)){
                map.put("endTime",DateAndTimeUtil.convert(endTime));
            }
            PageInfo<BackMoneyManageListVo> pageInfo = backMoneyService.backMoneyList(map, pageNum, pageSize);
            Integer count = backMoneyService.backMoneyListCount(map);
            Map map1 = new HashMap();
            map1.put("pageInfo",pageInfo);
            map1.put("count",count);
            result.setData(map1);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 借款人回款（status=1，还款成功 status = 2还款失败）
     * @param backMoney（传参:id,borrowMoneyUserId,status,productInfoId,count）
     * @return
     */
    @RequestMapping("/backMoney")
    @ResponseBody
    public JsonResult backMoney(BackMoney backMoney){
        JsonResult result = new JsonResult();
        try{
            Boolean b = backMoneyService.updateStatus(backMoney);
            if(b==true){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("操作成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("操作失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("操作失败");
        }
        return result;
    }
}
