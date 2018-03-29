package com.guanyitong.controller.borrowingManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.ProductService;
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
import java.util.Map;

@RequestMapping("/product")
@Controller
public class ProductManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;

    /**
     * 添加标种（产品）
     * @param product
     * @return
     */
    @RequestMapping("/insertProduct")
    @ResponseBody
    public JsonResult insertProduct(Product product){
        JsonResult result = new JsonResult();
        try{
            product.setCreateTime(new Date());
            Integer i = productService.insertProduct(product);
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
          result.setMessage("添加失败");
        }
        return result;
    }

    /**
     * 分页查询所有的标种（产品）
     * @param product
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllProducts")
    @ResponseBody
    public JsonResult selectAllProducts(Product product,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            if(pageNum !=null && pageSize !=null){
                PageInfo<Product> productPageInfo = productService.selectAllProducts(product, pageNum, pageSize);
                result.setState(JsonResult.SUCCESS);
                result.setData(productPageInfo);
                result.setMessage("返回数据成功");
            }
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
    public String toupdate(Long id,Model model){
        try{
            Product product = productService.selectProductById(id);
            model.addAttribute("product",product);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  "";
    }

    /**
     * 编辑chanpin
     * @param product
     * @return
     */
    @RequestMapping("/updateProduct")
    @ResponseBody
    public JsonResult updateProduct(Product product){
        JsonResult result = new JsonResult();
        try{
            product.setUpdateTime(new Date());
            Integer i = productService.updateProduct(product);
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

    /**
     * 删除产品
     * @param id
     * @return
     */
    @RequestMapping("/deleteProduct")
    @ResponseBody
    public JsonResult deleteProduct(Long id){
        JsonResult result = new JsonResult();
        try{
            Integer i = productService.deleteProduct(id);
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


    //============================================================投标（不同标种下。有不同类型的投标，每个投标都对应一个借款用户id）

    /**
     * 添加投标
     * @param productInfo
     * @return
     */
    @RequestMapping("/insertProductinfo")
    @ResponseBody
    public JsonResult insertProductinfo(ProductInfo productInfo){
        JsonResult result = new JsonResult();
        try{
            productInfo.setCreateTime(new Date());
            productInfo.setSYMoney(productInfo.getZMoney());//默认开始剩余可出借的金额与借款总额相等
            Integer i = productService.insertProductInfo(productInfo);
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
            result.setMessage("添加失败");
        }
        return result;
    }
    
    
    /**
     * 分页查询借款人的投标列表信息（条件查询）
     * 投标审核管理
     * @param productInfo
     * @param startTime
     * @param endTime
     * @param startBorrowTime
     * @param endBorrowTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectProductInfoVo")
    @ResponseBody
    public JsonResult selectBorrowInfo(ProductInfo productInfo,Date startTime,Date endTime,Date startBorrowTime,Date endBorrowTime,Integer pageNum,Integer pageSize){
          JsonResult result = new JsonResult();
          try{
              Map map = new HashMap();
              if(productInfo!=null){
                  if(productInfo.getBorrowMoneyUserId()!=null && !("").equals(productInfo.getBorrowMoneyUserId())){
                      map.put("borrowMoneyUserId",productInfo.getBorrowMoneyUserId());
                  }
                  if(productInfo.getNO()!=null && !("").equals(productInfo.getNO())){
                      map.put("NO",productInfo.getNO());
                  }
                  if(productInfo.getBackMoneyType()!=null && !("".equals(productInfo.getBackMoneyType()))){
                      map.put("backMoneyType",productInfo.getBackMoneyType());
                  }
                  if(productInfo.getStatus()!=null){
                      map.put("status",productInfo.getStatus());
                  }
              }
              if(startTime!=null){
                  map.put("startTime",startTime);
              }
              if(endTime!=null){
                  map.put("endTime",endTime);
              }
              if(startBorrowTime!=null){
                  map.put("startBorrowTime",startBorrowTime);
              }
              if(endBorrowTime!=null){
                  map.put("endBorrowTime",endBorrowTime);
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

    /**
     * 根据id查询借款明细（标的详情）
     * 投标审核管理模块（查看详情）
     * @param id
     * @return
     */
    @RequestMapping("/selectProductInfoById")
    @ResponseBody
   public JsonResult selectProductInfoById(Long id){
        JsonResult result = new JsonResult();
        try{
            UserProductInfoVo userProductInfoVo = productService.selectBorrowInfoById(id);//查询借款明细（标的详情）
            if(userProductInfoVo!=null){
                result.setState(JsonResult.SUCCESS);
                result.setData(userProductInfoVo);
                result.setMessage("返回数据成功");
            }else{
                result.setState(JsonResult.SUCCESS);
                result.setMessage("暂无数据");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
   }

   /* *//**
     * 查询某标种下的所有的投标（分页）
     * @param productInfo
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    @RequestMapping("/selectAllProductinfo")
    @ResponseBody
     public JsonResult selectAllProductinfo(ProductInfo productInfo,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            if(pageNum!=null && pageSize !=null){
                PageInfo<ProductInfo> productInfoPageInfo = productService.selectAllProductinfo(productInfo, pageNum, pageSize);
                result.setState(JsonResult.SUCCESS);
                result.setData(productInfoPageInfo);
                result.setMessage("返回数据成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
     }

    *//**
     * 删除投标
     * @param id
     * @return
     *//*
    @RequestMapping("/deleteProductinfo")
    @ResponseBody
     public JsonResult deleteProductinfo(Long id){
        JsonResult result = new JsonResult();
        try{
            Integer i = productService.deleteProductinfo(id);
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

    *//**
     * 查看投标详情，所有用户的此投标的信息
     * @param productInfoId
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    @RequestMapping("/selectUserProductinfo")
    @ResponseBody
     public JsonResult selectUserProductinfo(Long productInfoId,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            if(pageNum!=null && pageSize!=null){
                PageInfo<UserProductInfoVo> productInfoPageInfo = productService.selectUserProductinfo(productInfoId, pageNum, pageSize);
                result.setState(JsonResult.SUCCESS);
                result.setData(productInfoPageInfo);
                result.setMessage("返回数据成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
     }
*/
    /**
     * 跳转到修改标的页面（回显数据）
     * @param model
     * @param id
     * @return
     */
    public String toUpdate(Model model,Long id){
        try{
            UserProductInfoVo userProductInfoVo = productService.selectBorrowInfoById(id);
            model.addAttribute("userProductInfoVo",userProductInfoVo);//回显标的数据
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";//跳转到修改页面
    }

    /**
     * 投标审核管理，修改状态
     * 修改标的信息(审核标，即修改状态)
     * @param productInfo
     * @return
     */
    @RequestMapping("/updateProductInfo")
    @ResponseBody
    public JsonResult updateProductInfo(ProductInfo productInfo){
         JsonResult result = new JsonResult();
         try{
             Integer i = productService.updateProductInfo(productInfo);
             if(i>0){
                 result.setState(JsonResult.SUCCESS);
                 result.setMessage("修改成功");
             }
         }catch(Exception e){
             e.printStackTrace();
             result.setState(JsonResult.ERROR);
             result.setMessage("返回数据失败");
         }
         return result;
    }

}
