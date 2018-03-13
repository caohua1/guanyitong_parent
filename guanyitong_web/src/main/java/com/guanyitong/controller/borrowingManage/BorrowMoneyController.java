package com.guanyitong.controller.borrowingManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrowMoney")
public class BorrowMoneyController {
    //0未开始 1筹集中 2筹集完成（还款中） 3筹集完成（还款结束）4筹集失败 5下架

    //1.查询借款列表
    //2.未开始借款明细
    //3.筹集中借款明细----出借人列表
    //4.筹集完借款明细----出借人列表/还款计划

}
