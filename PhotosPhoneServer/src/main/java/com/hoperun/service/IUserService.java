package com.hoperun.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hoperun.exception.ServiceException;

public interface IUserService {

	/**
	 * 根据登录名获取密码和userId
	 * @param params
	 * @return
	 */
	Map<String, Object> selectLogin(Map<String, String> params) throws ServiceException;
	
	
}

