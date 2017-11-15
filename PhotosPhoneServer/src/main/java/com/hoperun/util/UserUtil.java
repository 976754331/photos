package com.hoperun.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UserUtil {
	
	//获取登录用户的用户id
	public static String getUserId(HttpServletRequest request){
		Object userObj = request.getSession().getAttribute("userId");
		
		String userId = "";
		if(userObj != null){
			userId = userObj.toString();
		}
		return userId;
	}

	
	
	
}
