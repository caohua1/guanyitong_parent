package com.guanyitong.controller;
import com.guanyitong.model.Treasure;
import com.guanyitong.model.UserGuanDou;
import com.guanyitong.model.UserTreasure;
import com.guanyitong.service.AccountManagerService;
import com.guanyitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateChangeUtil;
import util.JsonResult;

import java.util.*;

@Controller
@RequestMapping("/api")
public class IndexController {
    @Autowired
    private AccountManagerService accountManagerService;
    @Autowired
    private UserService userService;
    /**
     * 冠豆商城
     * 1.显示用户的冠豆数
     * 2.兑换记录
     * 3.赚取冠豆
     * 4.买商品
     * 5.兑换加息券和红包（查询所有的可以用冠豆兑换的宝箱之宝）
     */

    /**
     * 1.查询用户的冠豆数
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectGuandou",method= RequestMethod.GET)
    @ResponseBody
    public JsonResult selecGuandou(Long userId){
        JsonResult result = new JsonResult();
        try{
            int guandou = userService.selectGuanDou(userId);
            result.setState(JsonResult.SUCCESS);
            result.setData(guandou);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询某用户的冠豆详情（消费和获取）
     * @return
     */
    @RequestMapping(value = "/selectUserGuandouInfo",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectUserGuandouInfo(Long userId){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            Map XF = new HashMap();
            Map HQ = new HashMap();
            map.put("type",0);
            map.put("userId",userId);
            //消费（兑换的）的冠豆详情
            List<UserGuanDou> userGuanDous = userService.selectUserGuanDouInfo(map);
            if(userGuanDous.size()>0){
                int count = 0;
                for(UserGuanDou userGuanDou :userGuanDous){
                    count += userGuanDou.getGuanDou();
                }
                XF.put("XF",userGuanDous);
                XF.put("XF_count",count);
            }
            //获取的冠豆详情
            map.put("type",1);
            List<UserGuanDou> userGuanDous1 = userService.selectUserGuanDouInfo(map);
            if(userGuanDous1.size()>0){
                int count = 0;
                for(UserGuanDou userGuanDou : userGuanDous1){
                    count += userGuanDou.getGuanDou();
                }
                HQ.put("HQ",userGuanDous1);
                HQ.put("HQ_count",count);
            }
            List guandouInfo = new ArrayList();
            guandouInfo.add(XF);
            guandouInfo.add(HQ);
            result.setState(JsonResult.SUCCESS);
            result.setData(guandouInfo);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询所有的可以用冠豆兑换的宝箱之宝(展示列表)（type=0红包 3加息券）
     * @return
     */
    @RequestMapping(value = "/selectTreasure",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectTreasures(){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            String str ="0,3";
            map.put("type",str);
            List<Treasure> treasures = accountManagerService.selectTreasures(map);
            result.setState(JsonResult.SUCCESS);
            result.setData(treasures);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 5.兑换红包，加息券（如果冠豆数不够，不能兑换，在手机端判断）
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "/exchangeByGuandou",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult exchangeByGuandou(Long userId, Long id,Integer num){
        JsonResult result = new JsonResult();
        try{
            UserTreasure userTreasure = new UserTreasure();
            userTreasure.setUserId(userId);
            userTreasure.setNum(num);
            Map map = new HashMap();
            map.put("treasureId",id);
            Treasure treasure = accountManagerService.selectTreasure(map);//查询此宝箱的某个券的信息
            userTreasure.setTreasureId(id);
            Date startTime = new Date();
            userTreasure.setStartTime(startTime);
            userTreasure.setEndTime(DateChangeUtil.dateAddDays(startTime,treasure.getDays()));
            int i = accountManagerService.insertUserTreasure(userTreasure, treasure);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("兑换成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("兑换失败");
        }
        return result;
    }

}
