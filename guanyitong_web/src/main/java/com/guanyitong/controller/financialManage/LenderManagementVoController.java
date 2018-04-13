package com.guanyitong.controller.financialManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.LenderManagementVo;
import com.guanyitong.service.LenderManagementVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//出借人银行卡管理
@Controller
@RequestMapping("/LenderManagement")
public class LenderManagementVoController {

    @Autowired
    private LenderManagementVoService lenderManagementVoService;

    /**
     * 分页查询出借人银行卡信息
     * 条件查询出借人银行卡信息
     * @param lenderManagementVo
     * @param
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectLenderManagement")
    @ResponseBody
    public JsonResult selectLenderManagement(LenderManagementVo lenderManagementVo,
                                             @RequestParam(required=false)String first, @RequestParam(required=false)String last,
                                             @RequestParam(required=false)Integer pageNum, @RequestParam(required=false)Integer pageSize){
        JsonResult result = new JsonResult();
        Map<Object, Object> lenderMap = new HashMap<Object, Object>();
        DateAndTimeUtil dateAndTimeUtil = new DateAndTimeUtil();
        Date firstDate=null;
        Date lastDate =null;
        if(first!=null&&first!=""){
            firstDate = dateAndTimeUtil.convert(first);
        }
        if(last!=null&&last!=""){
            lastDate = dateAndTimeUtil.convert(last);
        }
        try{
        if(lenderManagementVo!=null){
            if(lenderManagementVo.getUsername()!=null && !("").equals(lenderManagementVo.getUsername())){
                lenderMap.put("username",lenderManagementVo.getUsername());
            }
            if(lenderManagementVo.getRealName()!=null&& !("").equals(lenderManagementVo.getRealName())){
                lenderMap.put("realName",lenderManagementVo.getRealName());
            }
            if(lenderManagementVo.getIdCard()!=null && !("").equals(lenderManagementVo.getIdCard())){
                lenderMap.put("idCard",lenderManagementVo.getIdCard());
            }
            if (lenderManagementVo.getBankNum()!=null&&!("".equals(lenderManagementVo.getBankNum()))) {
                lenderMap.put("bankNum",lenderManagementVo.getBankNum());
            }
            if(firstDate!=null){
                lenderMap.put("firstDate",firstDate);
            }
            if(lastDate!=null){
                lenderMap.put("lastDate",lastDate);
            }
        }
            PageInfo<LenderManagementVo> lenderManagementVo1 = lenderManagementVoService.selectLenderManagementVo(pageNum, pageSize,lenderMap);
            result.setData(lenderManagementVo1);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");

        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }


    /**
     * 根据id查询某个银行卡绑定的信息
     * @param
     * @return
     */
    @RequestMapping("/selectById")
    public String selectById(Model model, String userId){

        JsonResult result = new JsonResult();
        Long Id= Long.valueOf(userId);
        try{
            if(Id !=null){
                LenderManagementVo lenderManagementVo = lenderManagementVoService.selectByID(Id);
                if(lenderManagementVo!=null){
                    model.addAttribute("lender",lenderManagementVo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return "borrowUserBankManager/lenderManageMen_select";
    }
}
