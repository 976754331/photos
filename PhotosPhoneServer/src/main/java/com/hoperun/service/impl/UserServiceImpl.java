package com.hoperun.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public Map<String, Object> selectLogin(HttpServletRequest request, Map<String, String> params) throws ServiceException{
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
			rtnMap.put("userId", resultMap.get("user_id").toString());
			rtnMap.put("name", resultMap.get("name").toString());
			//return rtnMap;
		}else{
			rtnMap.put("flag", "0");
			rtnMap.put("msg", "输入密码错误");					
		}
		if("1".equals(rtnMap.get("flag"))){  //登录成功
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			String userId =  rtnMap.get("userId").toString();
			//每个账号只允许单个用户登录
			/**
			 * 将登陆信息存到ServletContext中			
			ServletContext sc = session.getServletContext();
			Object objMap = sc.getAttribute("existMap");
			Map<String, String> existMap = new HashMap<String, String>();
			if (objMap != null && objMap instanceof Map) {
				existMap = (Map<String, String>) objMap;
			}

			existMap.put(userId, sessionId);
			session.getServletContext().setAttribute("existMap", existMap);
			 */
			
			//后登录者干掉之前登录者  将登陆信息存到数据库中
			//根据userId查询session信息
			Map<String, String> sessionParam = new HashMap<String, String>();
			sessionParam.put("userId", userId);
			sessionParam.put("sessionId", sessionId);
			Map<String, Object> resMap = userMapper.selectSession(sessionParam);
			if(resMap == null){  //无session信息新增
				userMapper.insertSession(sessionParam);
			}else{  //有则更新
				userMapper.updateSession(sessionParam);
			}			
			session.setAttribute("userId", userId);
			rtnMap.put("sessionId", sessionId);
		}
		return rtnMap;
	}
	
	

	

	
}
