package com.hoperun.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoperun.exception.ServiceException;
import com.hoperun.mapper.UserMapper;
import com.hoperun.service.IUserService;

@Transactional
@Service
public class UserServiceImpl  implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据登录名获取密码和userId
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectLogin(Map<String, String> params) throws ServiceException{
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = userMapper.selectLogin(params);
		if(resultMap == null){
			rtnMap.put("flag", "0");
			rtnMap.put("msg", "用户名错误");	
			return rtnMap;
		}
		String password = resultMap.get("password").toString();
		String inputPassword = 	params.get("password");
		if(inputPassword == null){
			rtnMap.put("flag", "0");
			rtnMap.put("msg", "输入密码为空");	
			return rtnMap;		
		}
		if(password.equals(inputPassword)){
			rtnMap.put("flag", "1");
			rtnMap.put("msg", "密码正确");	
			return rtnMap;
		}else{
			rtnMap.put("flag", "0");
			rtnMap.put("msg", "输入密码错误");					
		}
		return rtnMap;
	}
	
	

	

	
}
