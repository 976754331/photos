package com.hoperun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.model.vo.ReturnInfo;

@Controller
@RequestMapping("/test")
public class TestController{
	
	@RequestMapping(value = "test")
	@ResponseBody
	public ReturnInfo test(HttpServletRequest request) {
		ReturnInfo rtn = new ReturnInfo();
		rtn.setData("test");
		return rtn;
	}

}
