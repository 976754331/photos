package com.hoperun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.exception.ServiceException;
import com.hoperun.model.vo.ReturnInfo;
import com.hoperun.service.IFileService;
import com.hoperun.service.IUserService;

/**
 * 用户登录及信息查询类
 * @author Wang_wei
 *
 */
@Controller
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 根据登录名获取密码和userId
	 * flag为1登录成功，flag为0登录成功
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public ReturnInfo login(HttpServletRequest request, String loginName, String password) {
		ReturnInfo rtn = new ReturnInfo();
		Map<String,String> params = new HashMap<String, String>();
		params.put("loginName", loginName);
		params.put("password", password);
		Map<String,Object> rtnMap = new HashMap<String, Object>();
		try {
			rtnMap = userService.selectLogin(params);
		} catch (ServiceException e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		rtn.setData(rtnMap);
		return rtn;
	}

	
}