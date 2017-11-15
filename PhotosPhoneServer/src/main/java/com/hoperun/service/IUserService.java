package com.hoperun.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hoperun.exception.ServiceException;

public interface IUserService {

	/**
	 * 根据登录名获取密码和userId
	 * @param request 
	 * @param params
	 * @return
	 */
	Map<String, Object> selectLogin(HttpServletRequest request, Map<String, String> params) throws ServiceException;
	
	
}

