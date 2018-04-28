package com.guanyitong.controller.EmployeeManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.PermissionDao;
import com.guanyitong.model.Permission;
import com.guanyitong.model.Role;
import com.guanyitong.model.RolePermission;
import com.guanyitong.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostService postService;
    @Autowired
    private PermissionDao permissionDao;
    /**
     * 添加职位
     * @param role
     * @return
     */
    @RequestMapping("/insertPost")
    @ResponseBody
    public JsonResult insertPost(Role role,String permissionIds){
        JsonResult result = new JsonResult();
        try{
            role.setPcreateTime(new Date());
            if(role !=null){
                Boolean b = postService.insertPost(role,permissionIds);
                if(b==true){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("添加成功");
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("添加失败");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("添加失败");
        }
        return result;
    }

    /**
     * 分页查询所有的职位（条件查询）
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllPost")
    @ResponseBody
    public JsonResult selectAllPost(Role role, Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<Role> pageInfo = postService.selectPost(role, pageNum, pageSize);
            Integer count = postService.selectPostCount(role);
            Map map = new HashMap();
            map.put("count",count);
            map.put("pageInfo",pageInfo);
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
     * 跳转到编辑页面，回显数据
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toupdate")
    public String toupdate(Long id, Model model){
        try{
            Role post = postService.selectPostById(id);
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

            //查询此角色的权限
            List<Permission> rolePermissions = postService.selectPermissionRole(id);
            model.addAttribute("post",post);
            model.addAttribute("permissions",permissions);
            model.addAttribute("rolePermissions",rolePermissions);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "permissionManager/role_update";
    }

    /**
     * 编辑职位
     * @param post
     * @return
     */
    @RequestMapping("/updatePost")
    @ResponseBody
    public JsonResult updatePost(Role post,String permissionIds){
        JsonResult result = new JsonResult();
        try{
            post.setPupdateTime(new Date());
            if(post !=null){
                Boolean b= postService.updatePost(post,permissionIds);
                if(b==true){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("修改成功");
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("修改失败");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deletePost")
    @ResponseBody
    public JsonResult deletePost(Long id){
        JsonResult result = new JsonResult();
        try{
            Integer i = postService.deletePost(id);
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
