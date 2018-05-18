package com.guanyitong.controller.toJsp;

import com.guanyitong.mapper.BorrowMoneyUserDao;
import com.guanyitong.mapper.PermissionDao;
import com.guanyitong.mapper.PostDAO;
import com.guanyitong.mapper.ProductDao;
import com.guanyitong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/toJsp")
@Controller
public class ToJspController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private BorrowMoneyUserDao borrowMoneyUserDao;
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private PermissionDao permissionDao;
    //==================================借款人（用户管理模块）

    /**
     * 跳转到借款人列表
     * @return
     */
    @RequestMapping("/toborrowUserList")
    public String toborrowUserList(){
        return "userManager/borrowUser_list";
    }

    /**
     * 跳转到添加借款人信息的页面
     * @return
     */
    @RequestMapping("/toAddBorrowUser")
    public String toAddBorrowUser(){
        return "userManager/borrowUser_add";
    }



    /**
     * 跳转到借款人认证资料审核
     * @return
     */
    @RequestMapping("/toborrowUserApprrove1")
    public String toborrowUserApprrove1(){
        return "userManager/borrowUser_apprrove1";
    }

    /**
     * 跳转到修改页面（认证资料审核未通过，可以修改全部）
     * @return
     */
    @RequestMapping("/toborrowUser_updateSta1")
    public String toborrowUser_updateSta1(Long id,Model model,Integer status,Integer Type){
        BorrowMoneyUser borrowMoneyUser = borrowMoneyUserDao.selectBorrowMoneyUser(id);
        model.addAttribute("borrowMoneyUser",borrowMoneyUser);
        model.addAttribute("status",status);
        model.addAttribute("Type",Type);
        return "userManager/borrowUser_updateSta1";
    }

    /**
     * 跳转到借款人借款额度审核
     * @return
     */
    @RequestMapping("/toborrowUserApprrove2")
    public String toborrowUserApprrove2(){
        return "userManager/borrowUser_apprrove2";
    }

    /**
     * 跳转到合同管理
     * @return
     */
    @RequestMapping("/toborrowUserApprrove3")
    public String toborrowUserApprrove3(){
        return "userManager/borrowUser_apprrove3";
    }

    /**
     * 跳转到借款用户统计
     * @return
     */
    @RequestMapping("/toborrowUserStatistics")
    public String totoborrowUserStatistics(){
        return "userManager/borrowUser_statistics";
    }

    /**
     * 跳转到出借用户注册统计
     * @return
     */
    @RequestMapping("/todealUserStatistics")
    public String dealUserStatistics(){
        return "userManager/dealUser_statistics";
    }

    /**
     * 跳转到资料审核的详情（查看）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toborrowUserApprrove1_info")
    public String toborrowUserApprrove1_info(Long id,Model model,Integer status){
        model.addAttribute("id",id);
        model.addAttribute("status",status);
        return "userManager/borrowUser_apprrove1_info";
    }

    /**
     * 跳转到额度申请的详情（查看）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toborrowUserApprrove2_info")
    public String toborrowUserApprrove2_info(Long id,Model model,Integer status){
        model.addAttribute("id",id);
        model.addAttribute("status",status);
        return "userManager/borrowUser_apprrove2_info";
    }

    /**
     * 跳转到合同管理（查看）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toborrowUserApprrove3_info")
    public String toborrowUserApprrove3_info(Long id,Model model,Integer status){
        model.addAttribute("id",id);
        model.addAttribute("status",status);
        return "userManager/borrowUser_apprrove3_info";
    }

    //=============================借款管理
    /**
     * 跳转到标种管理页面
     * @return
     */
    @RequestMapping("/toproductType_list")
    public String toproductType_list(){
        return "borrowManager/productType_list";
    }

    /**
     * 跳转到添加标种页面
     * @return
     */
    @RequestMapping("/toproductType_add")
    public String toproductType_add(){
        return "borrowManager/productType_add";
    }
    /**
     * 跳转到投标管理页面
     * @return
     */
    @RequestMapping("/toproductInfo_manage_list")
    public String toproductInfo_manage_list(){
        return "borrowManager/productInfo_manage_list";
    }

    /**
     * 跳转到添加投标的页面
     * @return
     */
    @RequestMapping("/toproductInfo_add")
    public String toproductInfo_add(Model model){
        Map map = new HashMap();
        map.put("id",null);
        List<Product> products = productDao.selectProduct(map);
        model.addAttribute("products",products);
        return "borrowManager/productInfo_add";
    }

    /**
     * 跳转投标审核管理页面
     * @return
     */
    @RequestMapping("/toproductInfo_apprrove_manage")
    public String toproductInfo_apprrove_manage(){
        return "borrowManager/productInfo_apprrove_manage";
    }

    /**
     * 查看投标详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/productinfo_manage_info")
    public String productinfo_manage_info(Long id,Integer status,Model model){
        model.addAttribute("id",id);
        model.addAttribute("status",status);
        return "borrowManager/productinfo_manage_info";
    }

    /**
     * 跳转到借款管理页面
     * @return
     */
    @RequestMapping("/toborrowMoney_manage")
    public String toborrowMoney_manage(){
        return "borrowManager/borrowMoney_manage";
    }

    /**
     * 借款管理，查看借款明细
     * @param id
     * @param status
     * @param model
     * @return
     */
    @RequestMapping("/toborrowMoney_info")
    public String toborrowMoney_info(Long id,Integer status,Model model,String borrowMoneyUserId){
        model.addAttribute("id",id);//标的id（productInfoId）
        model.addAttribute("status",status);
        model.addAttribute("borrowMoneyUserId",borrowMoneyUserId);
        return "borrowManager/borrowMoney_info";
    }
    /**
     * 修改产品（productInfo）
     * @return
     */
    @RequestMapping("/toUpdateProductInfo")
    public String toUpdateProductInfo(Long id,Model model){
        model.addAttribute("id",id);
        //查询所有的标种
        List<Product> products = productDao.selectAllProducts();
        model.addAttribute("products",products);
        //根据id查询此条数据
        Map map = new HashMap();
        map.put("productInfoId",id);
        List<ProductInfo> productInfos = productDao.selectProductInfo(map);
        model.addAttribute("productInfos",productInfos.get(0));
        return "borrowManager/update_productInfo";
    }

    /**
     * 提现收款管理页面
     * @return
     */
    @RequestMapping("/towithdrawBackMoney_manage")
    public String towithdrawBackMoney_manage(){
        return "borrowManager/withdrawBackMoney_manage";
    }

    /**
     * 出借用户统计页面
     * @return
     */
    @RequestMapping("/todealUserStatistics2")
    public String todealUserStatistics2(){
        return "borrowManager/dealUser_statistics";
    }


    //============================(财务管理模块)
    /**
     * 跳转到借款人银行卡管理
     * @return
     */
    @RequestMapping("/toBankCardManage")
    public String toBankCardManage(){ return "moneyManager/borrowUserBank";}

    /**
     * 跳转到添加用户银行卡页面
     * @return
     */
    @RequestMapping("/toAddBankCard")
    public String toAddBankCard(){return "moneyManager/borrowUserBank_add";}



    /**
     * 跳转到出借人银行卡管理页面
     * @return
     */
    @RequestMapping("/toLenderManageMent")
    public String toLenderManageMent(){
        return "moneyManager/lenderManageMent";
    }


    /**
     * 跳转到充值记录管理页面
     * @return
     */
    @RequestMapping("/toRechargeManagement")
    public String toRechargeManagement(){
        return "moneyManager/rechargeManagement";
    }

    /**
     * 跳转到资金账户管理页面
     * @return
     */
    @RequestMapping("/toCapitalAccount")
    public String toCapitalAccount(){
        return "moneyManager/capitalAccount";
    }

    /**
     * 跳转到还款管理页面
     * @return
     */
    @RequestMapping("/tobackMoney")
    public String tobackMoney(){
        return "moneyManager/backMoney";
    }


    /**
     * 跳转到提现管理页面
     * @return
     */
    @RequestMapping("/toCashManagement")
    public String toCashManagement(){
        return "moneyManager/cashManagement";
    }


    //========================权限管理

    /**
     * 跳转到员工管理列表页面
     * @param model
     * @return
     */
    @RequestMapping("/toEmployeeList")
    public String toEmployeeList(Model model){
        //查询所有的角色
        Role role = new Role();
        List<Role> roles = postDAO.selectPost(role);
        model.addAttribute("roles",roles);
        return "permissionManager/employee_list";
    }

    /**
     * 添加员工页面
     * @param model
     * @return
     */
    @RequestMapping("/toEmployeeAdd")
    public String toEmployeeAdd(Model model){
        //查询所有的角色
        Role role = new Role();
        List<Role> roles = postDAO.selectPost(role);
        model.addAttribute("roles",roles);
        return "permissionManager/employee_add";
    }

    /**
     * 跳转到角色管理列表页面
     * @return
     */
    @RequestMapping("/toRoleList")
    public String toRoleList(Model model){
        //查询所有的角色
        Role role = new Role();
        List<Role> roles = postDAO.selectPost(role);
        model.addAttribute("roles",roles);
        return "permissionManager/role_list";
    }

    /**
     * 跳转到添加角色页面
     * @param model
     * @return
     */
    @RequestMapping("/toRoleAdd")
    public String toRoleAdd(Model model){
        //查询所有的权限
        List<Permission> permissions = permissionDao.selectParentPermission();
        if(permissions!=null && permissions.size()>0){
            for(Permission permission : permissions){
                Map<String,List<Permission>> map = new HashMap<String, List<Permission>>();
                List<Permission> permissions1 = permissionDao.selectChildPermission(permission.getId());
                map.put("permission",permissions1);
                permission.setMap(map);
            }
        }
        model.addAttribute("permissions",permissions);
        return "permissionManager/role_add";
    }

    //===================================帮助中心模块
    /**
     * 跳转到常见问题页面（type=1）
     * @param type
     * @param model
     * @return
     */
    @RequestMapping("/toquestions")
    public String toquestions(Integer type,Model model){
        model.addAttribute("type",type);
        return "";
    }
}
