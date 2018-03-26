package com.guanyitong.controller.EmployeeManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Post;
import com.guanyitong.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostService postService;

    /**
     * 添加职位
     * @param post
     * @return
     */
    @RequestMapping("/insertPost")
    @ResponseBody
    public JsonResult insertPost(Post post){
        JsonResult result = new JsonResult();
        try{
            post.setPcreateTime(new Date());
            if(post !=null){
                Integer i = postService.insertPost(post);
                if(i>0){
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
     * @param post
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllPost")
    @ResponseBody
    public JsonResult selectAllPost(Post post,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<Post> pageInfo = postService.selectPost(post, pageNum, pageSize);
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
     * 跳转到编辑页面，回显数据
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toupdate")
    public String toupdate(Long id, Model model){
        try{
            Post post = postService.selectPostById(id);
           model.addAttribute("post",post);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 编辑职位
     * @param post
     * @return
     */
    @RequestMapping("/updatePost")
    @ResponseBody
    public JsonResult updatePost(Post post){
        JsonResult result = new JsonResult();
        try{
            post.setPupdateTime(new Date());
            if(post !=null){
                Integer i = postService.updatePost(post);
                if(i>0){
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
