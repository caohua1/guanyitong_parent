package com.guanyitong.controller.borrowingManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.JsonResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/borrowMoney")
public class BorrowMoneyController {
    //0未开始 1筹集中 2筹集完成（还款中） 3筹集完成（还款结束）4筹集失败 5下架

    //1.查询借款列表
    //2.未开始借款明细
    //3.筹集中借款明细----出借人列表
    //4.筹集完借款明细----出借人列表/还款计划
    @Autowired
    private ProductService productService;

    /**
     * 分页，条件查询 查询借款列表（借款流程）
     * @param userProductInfoVo
     * @param startTime
     * @param endTime
     * @return
     */
    public JsonResult selectBorrowInfo(UserProductInfoVo userProductInfoVo,Date startTime,Date endTime,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            map.put("borrowMoneyUserId",userProductInfoVo.getBorrowMoneyUserId());
            map.put("NO",userProductInfoVo.getNO());
            map.put("startTime",startTime);
            map.put("endTime",endTime);
            map.put("startBorrowTime",userProductInfoVo.getStartBorrowTime());
            map.put("endBorrowTime",userProductInfoVo.getEndBorrowTime());
            map.put("backMoneyType",userProductInfoVo.getBackMoneyType());
            map.put("status",userProductInfoVo.getStatus());
            PageInfo<UserProductInfoVo> productInfoVoPageInfo = productService.selectBorrowInfo(map, pageNum, pageSize);
            result.setState(JsonResult.SUCCESS);
            result.setData(productInfoVoPageInfo);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
