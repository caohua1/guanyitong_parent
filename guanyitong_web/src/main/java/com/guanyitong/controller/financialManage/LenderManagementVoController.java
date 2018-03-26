package com.guanyitong.controller.financialManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.LenderManagementVo;
import com.guanyitong.service.LenderManagementVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/LenderManagement")
public class LenderManagementVoController {

    @Autowired
    private LenderManagementVoService lenderManagementVoService;

    /**
     * 分页查询出借人银行卡信息
     * 条件查询出借人银行卡信息
     */
    @RequestMapping("/selectLenderManagement")
    @ResponseBody
    public JsonResult selectLenderManagement(@RequestParam(required=false)LenderManagementVo lenderManagementVo,
                                             @RequestParam(required=false)Date firstDate, @RequestParam(required=false)Date lastDate,
                                             @RequestParam(required=false)Integer pageNum, @RequestParam(required=false)Integer pageSize){
        JsonResult result = new JsonResult();
        Map<Object, Object> lenderMap = new HashMap<Object, Object>();
        try{
        if(lenderManagementVo!=null){
            if(lenderManagementVo.getId()!=null){
                lenderMap.put("id",lenderManagementVo.getId());
            }
            if(lenderManagementVo.getUsername()!=null && !("").equals(lenderManagementVo.getUsername())){
                lenderMap.put("username",lenderManagementVo.getUsername());
            }
            if(lenderManagementVo.getRealName()!=null&& !("").equals(lenderManagementVo.getRealName())){
                lenderMap.put("realName",lenderManagementVo.getRealName());
            }
            if(lenderManagementVo.getIdCard()!=null && !("").equals(lenderManagementVo.getIdCard())){
                lenderMap.put("idCard",lenderManagementVo.getIdCard());
            }if(firstDate!=null){
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

    @RequestMapping("/selectById")
    @ResponseBody
    public JsonResult selectById(Integer id){
        JsonResult result = new JsonResult();
        Map<Object, Object> IDMap = new HashMap<Object, Object>();
        IDMap.put("id",id);
        try{
            LenderManagementVo lenderManagementVo = lenderManagementVoService.selectByID(IDMap);
            result.setData(lenderManagementVo);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
