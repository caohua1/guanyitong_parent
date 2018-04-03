package com.guanyitong.controller.toJsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/toJsp")
@Controller
public class ToJspController {

    //==================================借款人（用户管理模块）

    /**
     * 跳转到借款人列表
     * @return
     */
    @RequestMapping("/toborrowUserList")
    public String toborrowUserList(){
        return "borrowUserManager/borrowUser_approve";
    }

    /**
     * 跳转到添加借款人信息的页面
     * @return
     */
    @RequestMapping("/toAddBorrowUser")
    public String toAddBorrowUser(){
        return "borrowUserManager/borrowUser_add";
    }

    //============================借款人(财务管理)
    /**
     * 跳转到借款人银行卡管理
     * @return
     */
    @RequestMapping("/toBankCardManage")
    public String toBankCardManage(){ return "borrowUserManager/borrowUserBank";}
}
