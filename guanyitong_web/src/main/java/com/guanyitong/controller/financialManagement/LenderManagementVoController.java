package com.guanyitong.controller.financialManagement;

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
     */
    @RequestMapping("/listLenderManagement")
    @ResponseBody
    public JsonResult listLenderManagement(Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
       try{
           PageInfo<LenderManagementVo> lenderManagementVoPageInfo = lenderManagementVoService.listLenderManagementVo(pageNum, pageSize);
           result.setData(lenderManagementVoPageInfo);
           result.setState(JsonResult.SUCCESS);
           result.setMessage("返回数据成功");
       } catch (Exception e){
           e.printStackTrace();
           result.setState(JsonResult.ERROR);
           result.setMessage("返回数据失败");
       }
        return result;
    }

    /**
     * 模糊查询出借人银行卡信息
     */
    @RequestMapping("/selectLenderManagement")
    @ResponseBody
    public JsonResult selectLenderManagement(@RequestParam(required=false)LenderManagementVo lenderManagementVo, @RequestParam(required=false)Date firstDate, @RequestParam(required=false)Date lastDate){
        JsonResult result = new JsonResult();
        Map<Object, Object> lenderMap = new HashMap<Object, Object>();
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
            }if(firstDate!=null){
                lenderMap.put("firstDate",firstDate);
            }if(lastDate!=null){
                lenderMap.put("lastDate",lastDate);
            }
            LenderManagementVo lenderManagementVo1 = lenderManagementVoService.selectLenderManagementVo(lenderMap);
            result.setData(lenderManagementVo1);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
