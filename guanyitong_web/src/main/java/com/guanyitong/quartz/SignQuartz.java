package com.guanyitong.quartz;
import com.guanyitong.model.UserSignCalc;
import com.guanyitong.service.UserSignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public class SignQuartz {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserSignService userSignService;
    /**
     * 定时执行任务
     */
    @Transactional
    public void execute(){
        try{
            userSignService.deleteUserSignDetail();//1号清空所有的签到记录签到记录
            //查询所有用户的签到次数，有奖励（当月累计7天/14天/21天/28天可额外获得奖励）
            List<UserSignCalc> userSignCalcs = userSignService.selectAllUserSignNums();
            if(userSignCalcs!=null && userSignCalcs.size()>0){
                for(UserSignCalc userSignCalc : userSignCalcs){
                    Integer days = userSignCalc.getContinueDays();
                    Long userId = userSignCalc.getUserId();
                    if(days>=28){//不同的天数，奖励不同

                    }else if(days>=21){

                    }else if (days>=14){

                    }else if(days>=7){

                    }
                }
            }
            userSignService.deleteUserSignCalc();
            logger.info("执行了hahahah");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
