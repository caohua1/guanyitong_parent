package com.guanyitong.controller.app;
import com.guanyitong.model.Product;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/api")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    /**
     * 查询所有的产品
     * @return
     */
    @RequestMapping(value = "/selectAllProduct",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectAllProduct(){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            List<Product> products = productService.selectProduct(map);
            if(products!=null){
                result.setState(JsonResult.SUCCESS);
                result.setData(products);
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
     * 查询某种产品的详细信息（每种产品有不同期限的）
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectProductInfo",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectProductInfo(Long id){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            map.put("id",id);
            List<Product> products = productService.selectProduct(map);
            if(products!=null){
                for(Product product : products){
                    List<ProductInfo> productInfos = productService.selectProductInfo(map);
                    product.setProductInfos(productInfos);
                }
                result.setState(JsonResult.SUCCESS);
                result.setData(products);
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
     * 输入金额后，查看收益详情
     * @return
     */
    @RequestMapping("/earning")
    @ResponseBody
    public JsonResult earning(Integer dealMoney,ProductInfo productInfo){
        JsonResult result = new JsonResult();
        try{

        }catch(Exception e){

        }
        return result;
    }
}
