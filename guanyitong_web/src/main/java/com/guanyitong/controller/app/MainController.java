package com.guanyitong.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("index")
	public String index(){
		System.out.println(111);
		return "/HT_jsp/index.jsp";
	}
	
}
