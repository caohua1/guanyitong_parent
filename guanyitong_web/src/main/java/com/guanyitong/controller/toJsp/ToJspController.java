package com.guanyitong.controller.toJsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "borrowUserManager/borrowUser_list";
    }

    /**
     * 跳转到添加借款人信息的页面
     * @return
     */
    @RequestMapping("/toAddBorrowUser")
    public String toAddBorrowUser(){
        return "borrowUserManager/borrowUser_add";
    }



    /**
     * 跳转到借款人认证资料审核
     * @return
     */
    @RequestMapping("/toborrowUserApprrove1")
    public String toborrowUserApprrove1(){
        return "borrowUserManager/borrowUser_apprrove1";
    }

    /**
     * 跳转到借款人借款额度审核
     * @return
     */
    @RequestMapping("/toborrowUserApprrove2")
    public String toborrowUserApprrove2(){
        return "borrowUserManager/borrowUser_apprrove2";
    }

    /**
     * 跳转到合同管理
     * @return
     */
    @RequestMapping("/toborrowUserApprrove3")
    public String toborrowUserApprrove3(){
        return "borrowUserManager/borrowUser_apprrove3";
    }

    /**
     * 跳转到借款用户统计
     * @return
     */
    @RequestMapping("/toborrowUserStatistics")
    public String totoborrowUserStatistics(){
        return "borrowUserManager/borrowUser_statistics";
    }

    /**
     * 跳转到出借用户注册统计
     * @return
     */
    @RequestMapping("/todealUserStatistics")
    public String dealUserStatistics(){
        return "borrowUserManager/dealUser_statistics";
    }

    /**
     * 跳转到资料审核的详情（查看）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toborrowUserApprrove1_info")
    public String toborrowUserApprrove1_info(Long id,Model model){
        model.addAttribute("id",id);
        return "borrowUserManager/borrowUser_apprrove1_info";
    }

    /**
     * 跳转到额度申请的详情（查看）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toborrowUserApprrove2_info")
    public String toborrowUserApprrove2_info(Long id,Model model){
        model.addAttribute("id",id);
        return "borrowUserManager/borrowUser_apprrove2_info";
    }

    /**
     * 跳转到合同管理（查看）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toborrowUserApprrove3_info")
    public String toborrowUserApprrove3_info(Long id,Model model){
        model.addAttribute("id",id);
        return "borrowUserManager/borrowUser_apprrove3_info";
    }


    //============================借款人(财务管理)
    /**
     * 跳转到借款人银行卡管理
     * @return
     */
    @RequestMapping("/toBankCardManage")
    public String toBankCardManage(){ return "borrowUserBankManager/borrowUserBank";}

    /**
     * 跳转到添加用户银行卡页面
     * @return
     */
    @RequestMapping("/toAddBankCard")
    public String toAddBankCard(){return "borrowUserBankManager/borrowUserBank_add";}

}
