package com.guanyitong.controller.EmployeeManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Dept;
import com.guanyitong.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import java.util.Date;


@Controller
@RequestMapping("/dept")
public class DeptController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeptService deptService;

    /**
     * 查询所有的部门（分页）
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllDept")
    @ResponseBody
    public JsonResult selectAllDept(Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<Dept> pageInfo = deptService.selectAllDept(pageNum, pageSize);
            result.setState(JsonResult.SUCCESS);
            result.setData(pageInfo);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 下拉菜单显示所有的部门
     * @return
     */
  /*  @RequestMapping("/selectDepts")
    @ResponseBody
    public JsonResult selectDepts(){
        JsonResult result = new JsonResult();
        try{
            List<Dept> depts = deptService.selectAllDept();
            result.setState(JsonResult.SUCCESS);
            result.setData(depts);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("网络异常");
        }
        return result;
    }*/


    /**
     * 添加部门
     * @param dept
     * @return
     */
    @RequestMapping("/insertDept")
    @ResponseBody
    public JsonResult insertDept(Dept dept){
        JsonResult result = new JsonResult();
        try{
            dept.setDcreateTime(new Date());
            Integer i = deptService.insertDept(dept);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("添加成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("返回数据失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 编辑页面，回显数据
     * @param id
     * @return
     */
    @RequestMapping("/selectDeptById")
    @ResponseBody
    public JsonResult selectDeptById(Long id){
        JsonResult result = new JsonResult();
        try{
            Dept dept = deptService.selectDeptById(id);
            if(dept != null){
                result.setState(JsonResult.SUCCESS);
                result.setData(dept);
                result.setMessage("返回数据成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("返回数据失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
    /**
     * 编辑部门
     * @return
     */
    @RequestMapping("/updateDept")
    @ResponseBody
    public JsonResult updateDept(Dept dept){
        JsonResult result = new JsonResult();
        try{
            dept.setDupdateTime(new Date());
            if(dept!=null){
                Integer i = deptService.updateDept(dept);
                if(i>0){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("编辑成功");
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("编辑失败");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("编辑失败");
        }
        return result;
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping("/deleteDept")
    @ResponseBody
    public JsonResult deleteDept(Long id){
        JsonResult result = new JsonResult();
        try{
            Integer i = deptService.deleteDept(id);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("删除成功 ");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("删除失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("删除失败");
        }
        return result;
    }
}
