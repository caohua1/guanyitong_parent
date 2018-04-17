package com.guanyitong.quartz;

import com.guanyitong.mapper.ProductDao;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaiseQuartz {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;
    /**
     * 定时执行任务
     */
    @Transactional
    public void execute(){
        try{
            Date date = new Date();
            List<UserProductInfoVo> userProductInfoVos = productService.selectAllBorrowInfo();
            if(userProductInfoVos!=null && userProductInfoVos.size()>0){
                for(UserProductInfoVo userProductInfoVo : userProductInfoVos ){
                    Date startRaiseTime = userProductInfoVo.getStartRaiseTime();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(startRaiseTime!=null){
                        if(simpleDateFormat.format(startRaiseTime).equals(simpleDateFormat.format(date))){
                           Map map = new HashMap();
                           map.put("status",4);//筹集中
                           map.put("status1",2);
                           map.put("id",userProductInfoVo.getId());
                           map.put("updateTime",new Date());
                            Integer i = productDao.updateStatus(map);
                            if(i>0){
                                logger.info("执行了修改操作，只修改状态为2的，即审核完成的");
                            }else{
                                logger.info("未执行修改操作，因为可能还没有审核完成");
                            }
                        }
                    }
                }
            }
            logger.info("定时器执行了");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
