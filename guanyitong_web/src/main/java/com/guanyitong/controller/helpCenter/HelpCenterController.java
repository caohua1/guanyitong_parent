package com.guanyitong.controller.helpCenter;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.HelpCenter;
import com.guanyitong.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/helpCenter")
public class HelpCenterController {

    @Autowired
    private HelpCenterService helpCenterService;

    /**
     * 添加帮助中心主题
     * @param helpCenter
     * @return
     */
    @RequestMapping("/insertHelpCenter")
    @ResponseBody
    public JsonResult insertHelpCenter(HelpCenter helpCenter){
        JsonResult result = new JsonResult();
        try{
            helpCenter.setCreateTime(new Date());
            Integer i = helpCenterService.insertHelpCenter(helpCenter);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("添加成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("添加失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("网络问题");
        }
        return result;
    }

    /**
     * 分页查询所有的问题内容
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectHelpCenter")
    @ResponseBody
    public JsonResult selectHelpCenter(Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<HelpCenter> pageInfo = helpCenterService.selectHelpCenter(pageNum, pageSize);
            Map map = new HashMap();
            map.put("pageInfo",pageInfo);
            Integer count = helpCenterService.selectHelpCenterCount();
            map.put("count",count);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
            result.setData(map);
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("查询失败");
        }
        return result;
    }

    /**
     * 跳转到修改页面,回显数据
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toupdate")
    public String toupdate(Long id,Model model){
        HelpCenter helpCenter = helpCenterService.selectHelpCenterById(id);
        model.addAttribute("helpCenter",helpCenter);
        return "";
    }

    /**
     * 修改或删除
     * @param helpCenter
     * @return
     */
    @RequestMapping("/updateHelpCenter")
    @ResponseBody
    public JsonResult updateHelpCenter(HelpCenter helpCenter){
        JsonResult result = new JsonResult();
        try{
            helpCenter.setUpdateTime(new Date());
            Integer i = helpCenterService.updateHelpCenter(helpCenter);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("修改成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("修改失败");
        }
        return result;
    }
}
