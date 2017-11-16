package com.hoperun.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hoperun.mapper.UserMapper;
/**
 * session拦截器，只允许一台设备登录账号
 * @author houyaohui
 *
 */
public class SessionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		return true;
		// TODO Auto-generated method stub
		/*
		//暂时去掉过滤器，方便开发
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (null == url || url.contains("/logout.do") || url.contains("/login.do")) {
			return true;
		}
		HttpSession session = request.getSession();
		if(session == null){
			return false;
		}
		if(session.getAttribute("userId") == null){
			return false;
		}
		String userId = session.getAttribute("userId").toString();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		Map<String, Object> sessionMap = userMapper.selectSession(params);
		if(sessionMap != null && sessionMap.get("session_id") != null){
			if(sessionMap.get("session_id").toString().equals(session.getId())){  //session正确
				return true;
			}else{
				PrintWriter pw = response.getWriter();
				String retStr = "{\"msg\":\"请求超时,请重新登录\",\"data\":\"\",\"rtn_code\":10000}";
				pw.write(retStr);
				return false;
			}
		}
		
		return true;
		*/
		
		/**
		 * 与ServletContext中的数据做比较
		ServletContext context = request.getSession().getServletContext();
		Map<String, String> existMap = (Map<String, String>) context
				.getAttribute("existMap");
		if (existMap == null) {
			existMap = new HashMap<String, String>();
		} else {
			if (existMap.containsKey(userId)) {
				String jsessionId = existMap.get(userId);
				if (!jsessionId.equals(httpSession.getId())) {
					//httpSession.setAttribute("userId", userId);
					//existMap.put("userId", jsessionId);
					PrintWriter pw = response.getWriter();
					String retStr = "{\"msg\":\"请求超时,请重新登录\",\"data\":\"\",\"rtn_code\":10000}";
					pw.write(retStr);
					return false;
				}
			} else {
				existMap.put(userId, httpSession.getId());
			}
		}
		context.setAttribute("existMap", existMap);
		 */
		
	}

}
