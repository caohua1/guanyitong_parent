package com.guanyitong.controller.app;
import com.guanyitong.model.UserSignDetail;
import com.guanyitong.service.UserSignService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import java.util.Date;
@RequestMapping("/api")
@Controller
public class UserSignController {
    @Autowired
    private UserSignService userSignService;

    /**
     * 用户签到
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userDoSign",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult userSign(Long userId){
        JsonResult result = new JsonResult();
        try{
            //先判断用户近日是否已经签到了
            UserSignDetail userSignDetail = userSignService.selectUserBeforeSignDate(userId);
            Date date = new Date();
            boolean b = false;
            if(userSignDetail!=null){
                 b = sameDate(userSignDetail.getSignDate(), date);
            }
            if(b==true){//已经签到
                result.setState(JsonResult.ERROR);
                result.setMessage("已经签到");
            }else{
                UserSignDetail userSignDetail1 = new UserSignDetail();
                userSignDetail1.setUserId(userId);
                userSignDetail1.setSignDate(date);
                int i = userSignService.insertUserSignDetail(userSignDetail1);//签到成功后，获取冠豆(还未实现)
                if(i>0){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("签到成功");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("签到失败");
        }
        return result;
    }



  /* //比较两个日期是否相同（忽略时分秒），Java8中java.time提供的方法
    private static boolean sameDate(Date d1,Date d2){
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isEqual(localDate2);
    }*/
    public static boolean sameDate(Date d1, Date d2){
       return DateUtils.isSameDay(d1, d2);
    }
}
