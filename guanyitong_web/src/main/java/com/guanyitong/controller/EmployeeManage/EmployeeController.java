package com.guanyitong.controller.EmployeeManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.PostDAO;
import com.guanyitong.model.Dept;
import com.guanyitong.model.Employee;
import com.guanyitong.model.Role;
import com.guanyitong.model.vo.EmployeeVo;
import com.guanyitong.service.DeptService;
import com.guanyitong.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private PostDAO postDAO;
    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    @RequestMapping("/insertEmployee")
    @ResponseBody
    public JsonResult insertEmployee(Employee employee){
        JsonResult result = new JsonResult();
        try{
            employee.setEcreateTime(new Date());
            Integer i = employeeService.insertEmployee(employee);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("添加员工信息成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("添加员工失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("添加员工失败");
        }
        return result;
    }

    /**
     * 查询员工信息列表（分页，条件查询）
     * @param employeeVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectEmployee")
    @ResponseBody
    public JsonResult selectEmployee(EmployeeVo employeeVo, Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<EmployeeVo> pageInfo = employeeService.selectEmployee(employeeVo, pageNum, pageSize);
            Integer count = employeeService.selectEmployeeCount(employeeVo);
            Map map = new HashMap();
            map.put("pageInfo",pageInfo);
            map.put("count",count);
            result.setState(JsonResult.SUCCESS);
            result.setData(map);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 进入员工编辑页面，回显数据，下拉菜单的显示
     * @param model
     * @param id 员工
     * @return
     */
    @RequestMapping("/toupdate")
    public String toupdate(Model model,Long id){
         try{
             Employee employee = employeeService.selectEmployeeById(id);
             List<Dept> depts = deptService.selectAllDept();
             //查询所有的角色
             Role role = new Role();
             List<Role> roles = postDAO.selectPost(role);
             model.addAttribute("roles",roles);
             //查询所有员工职位（下拉菜单）
             model.addAttribute("employee", employee);
             model.addAttribute("depts", depts);
             model.addAttribute("id",id);
             //model.addAttribute("posts", posts);
         }catch(Exception e){
             e.printStackTrace();
         }
         return "permissionManager/employee_update";
    }
    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public JsonResult updateEmployee(Employee employee){
        JsonResult result = new JsonResult();
        try{
            employee.setEupdateTime(new Date());
            Integer i = employeeService.updateEmployee(employee);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("修改成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("修改失败");
        }
        return result;
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteEmployee")
    @ResponseBody
    public JsonResult deleteEmployee(Long id){
        JsonResult result = new JsonResult();
         try{
             Integer i = employeeService.deleteEmployee(id);
             if(i>0){
                 result.setState(JsonResult.SUCCESS);
                 result.setMessage("删除成功");
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
