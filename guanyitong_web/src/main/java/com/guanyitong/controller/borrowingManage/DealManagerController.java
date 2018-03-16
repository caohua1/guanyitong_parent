package com.guanyitong.controller.borrowingManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.UserDealMoneyVo;
import com.guanyitong.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/deal")
public class DealManagerController {
    @Autowired
    private UserDealService userDealService;

    /**
     * 出借用户统计（分页）
     * @param userDealMoneyVo
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllUserDeal")
    @ResponseBody
    public JsonResult selectAllUserDeal(UserDealMoneyVo userDealMoneyVo, @RequestParam(required=false)Date startTime,
                                        @RequestParam(required=false)Date endTime,@RequestParam(required=false)Integer minMoney,
                                        @RequestParam(required=false)Integer maxMoney,Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(userDealMoneyVo!=null){
                if(userDealMoneyVo.getUsername() !=null && !("").equals(userDealMoneyVo.getUsername())){
                    map.put("username",userDealMoneyVo.getUsername() );
                }
                if(userDealMoneyVo.getName() !=null && !("").equals(userDealMoneyVo.getName() )){
                    map.put("name",userDealMoneyVo.getName());
                }
                if(userDealMoneyVo.getIdCard()!=null &&!("").equals(userDealMoneyVo.getIdCard())){
                    map.put("idCard",userDealMoneyVo.getIdCard());
                }
                if(startTime !=null){
                    map.put("startTime",startTime);
                }
                if(endTime !=null){
                    map.put("endTime",endTime);
                }
                if(minMoney !=null){
                    map.put("minMoney",minMoney);
                }
                if(maxMoney !=null){
                    map.put("maxMoney",maxMoney);
                }
            }
            PageInfo<UserDealMoneyVo> pageInfo = userDealService.selectAllUserDeal(map, pageNum, pageSize);
            result.setState(JsonResult.SUCCESS);
            result.setData(pageInfo);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }


}
