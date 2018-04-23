package com.guanyitong.controller.borrowingManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.vo.BackMoneyVo;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.BackMoneyService;
import com.guanyitong.service.ProductService;
import com.guanyitong.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/borrowMoney")
public class BorrowMoneyController {
    //0未开始 1筹集中 2筹集完成（满标）（待提现） 3筹集失败 4下架

    //1.查询借款列表
    //2.未开始借款明细
    //3.筹集中借款明细----出借人列表
    //4.筹集完借款明细----出借人列表/还款计划

    //5.满标借款列表（筹集完的，可以提现的）
    //6.申请提现
    //7.确认收款
    @Autowired
    private ProductService productService;
    @Autowired
    private UserDealService userDealService;
    @Autowired
    private BackMoneyService backMoneyService;


    /**
     * 投标审核管理
     * 借款管理
     * 投标管理
     * 分页，条件查询 查询借款列表（借款流程）
     * 0待审核 1审核完成 2审核失败 3未开始 4筹集中 5筹集完成 6筹集失败 7上架 8下架 9放弃
     * @param userProductInfoVo
     * @param startTime
     * @param endTime
     * @return
     */
  /*  @RequestMapping("/selectBorrowInfo")
    @ResponseBody
    public JsonResult selectBorrowInfo(@RequestParam(required=false)UserProductInfoVo userProductInfoVo, @RequestParam(required=false)Date startTime,
                                       @RequestParam(required=false)Date endTime, Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(userProductInfoVo !=null){
                if(userProductInfoVo.getBorrowMoneyUserId()!=null && !("").equals(userProductInfoVo.getBorrowMoneyUserId())){
                    map.put("borrowMoneyUserId",userProductInfoVo.getBorrowMoneyUserId());
                }
                if(userProductInfoVo.getNO()!=null && !("").equals(userProductInfoVo.getNO())){
                    map.put("NO",userProductInfoVo.getNO());
                }
                if(startTime !=null){
                    map.put("startTime",startTime);
                }
                if(endTime !=null){
                    map.put("endTime",endTime);
                }
                if(userProductInfoVo.getStartBorrowTime() !=null && !("").equals(userProductInfoVo.getStartBorrowTime())){
                    map.put("startBorrowTime",userProductInfoVo.getStartBorrowTime());
                }
                if(userProductInfoVo.getEndBorrowTime()!=null && !("").equals(userProductInfoVo.getEndBorrowTime())){
                    map.put("endBorrowTime",userProductInfoVo.getEndBorrowTime());
                }
                if(userProductInfoVo.getBackMoneyType()!=null && !("").equals(userProductInfoVo.getBackMoneyType())){
                    map.put("backMoneyType",userProductInfoVo.getBackMoneyType());
                }
                if(userProductInfoVo.getStatus()!=null && !("").equals(userProductInfoVo.getStatus())){
                    map.put("status",userProductInfoVo.getStatus());
                }
            }
            PageInfo<UserProductInfoVo> productInfoVoPageInfo = productService.selectBorrowInfo(map, pageNum, pageSize);
            result.setState(JsonResult.SUCCESS);
            result.setData(productInfoVoPageInfo);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
*/
    /**
     * 投标审核管理（审核）
     * 修改标的状态0待审核 1审核完成 2审核失败 ,未开始 4筹集中 5筹集完成，待提现 6筹集失败 7再上架，筹集中 8下架 9放弃
     *  10提现申请中 11提现成功 12提现失败
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JsonResult updateStatus(Long id,Integer status){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            map.put("id",id);
            map.put("status",status);
            map.put("updateTime",new Date());
            Boolean b = productService.updateStatus(map);
            if(b==true){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("操作成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("操作失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("操作失败");
        }
        return result;
    }

    /**
     * 投标审核管理
     * 投标管理
     * 借款管理
     * 查看满标
     * 根据id查询借款明细（status ==4，5 借款人数统计）
     * @param id
     * @return
     */
    @RequestMapping("/selectBorrowInfoById")
    @ResponseBody
    public JsonResult selectBorrowInfoById(Long id){
        JsonResult result = new JsonResult();
        try{
            UserProductInfoVo userProductInfoVo = productService.selectBorrowInfoById(id);
            if(userProductInfoVo !=null && (userProductInfoVo.getStatus()==4 || userProductInfoVo.getStatus()==5)){
                //查询人数，根据productInfoId
                Integer count = userDealService.selectCountByProductInfoId(userProductInfoVo.getProductId());
                userProductInfoVo.setCount(count);
            }
            result.setData(userProductInfoVo);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询还款计划
     * @param borrowMoneyUserId(必传)
     * @param productInfoId（必传）
     * @return
     */
    @RequestMapping("/selectAllBackMoney")
    @ResponseBody
    public JsonResult selectAllBackMoney(String borrowMoneyUserId,Long productInfoId,String NO){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            map.put("borrowMoneyUserId",borrowMoneyUserId);
            map.put("productInfoId",productInfoId);
            map.put("NO",NO);
            BackMoneyVo backMoneyVo = backMoneyService.selectBackMoney(map);
            if(backMoneyVo!=null){
                result.setState(JsonResult.SUCCESS);
                result.setData(backMoneyVo);
                result.setMessage("返回数据成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("暂无数据");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 跳转到修改页面，回显数据（产品类型，投标信息）
     * @param id
     * @param model
     * @return
     */
    public String toupdate(Long id, Model model){
        try{
            //查询投标信息
            UserProductInfoVo userProductInfoVo = productService.selectBorrowInfoById(id);
            //查询产品类型，下拉菜单显示
            Map map = new HashMap();
            List<Product> products = productService.selectProduct(map);
            model.addAttribute("userProductInfoVo",userProductInfoVo);
            model.addAttribute("products",products);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 修改投标
     * 投标审核管理
     * 审核投标
     * @param productInfo
     * @return
     */
    @RequestMapping("/updateProductInfo")
    @ResponseBody
    public JsonResult updateProductInfo(ProductInfo productInfo){
        JsonResult result = new JsonResult();
        try{
            productInfo.setStatus(0);
            productInfo.setUpdateTime(new Date());
            Integer i = productService.updateProductInfo(productInfo);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("修改投标信息成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 根据标id查询出借人列表（分页）
     * @param productInfoId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectUserProductinfo")
    @ResponseBody
    public JsonResult selectUserProductinfo(Long productInfoId,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<UserProductInfoVo> productInfoVoPageInfo = productService.selectUserProductinfo(productInfoId, pageNum, pageSize);
            Integer count = productService.selectUserProductinfoCount(productInfoId);
            Map map = new HashMap();
            map.put("pageInfo",productInfoVoPageInfo);
            map.put("count",count);
            result.setState(JsonResult.SUCCESS);
            result.setData(map);
            result.setMessage("返回出借人列表成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

}
