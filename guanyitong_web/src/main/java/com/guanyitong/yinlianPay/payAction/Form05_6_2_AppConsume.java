package com.guanyitong.yinlianPay.payAction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Form05_6_2_AppConsume")
public class Form05_6_2_AppConsume {

    @RequestMapping("/yinlianPay")
    public String yinlianPay(HttpServletRequest req, HttpServletResponse resp){
        String merId = req.getParameter("merId");
        String txnAmt = req.getParameter("txnAmt");
        String orderId = req.getParameter("orderId");
        String txnTime = req.getParameter("txnTime");
        Map<String, String> contentData = new HashMap<String, String>();

        return null;
    }
}
