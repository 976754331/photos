package com.hoperun.mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper{
	
	/**
	 * 根据登录名获取密码和userId
	 * @param params
	 * @return
	 */
	Map<String, Object> selectLogin(Map<String, String> params);
	
	/**
	 * 根据userId查询用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> selectUserInfo(Map<String, String> params);
}
