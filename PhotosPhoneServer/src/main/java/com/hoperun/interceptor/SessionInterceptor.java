package com.hoperun.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * session拦截器，只允许一台设备登录账号
 * @author houyaohui
 *
 */
public class SessionInterceptor implements HandlerInterceptor {
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
		// TODO Auto-generated method stub
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (null == url || url.contains("/logout.do") || url.contains("/login.do")) {
			return true;
		}
		String userId = request.getParameter("userFlag");
		HttpSession httpSession = request.getSession();
		ServletContext context = request.getSession().getServletContext();
		Map<String, String> existMap = (Map<String, String>) context
				.getAttribute("existMap");
		if (existMap == null) {
			existMap = new HashMap<String, String>();
		} else {
			if (existMap.containsKey(userId)) {
				String jsessionId = existMap.get(userId);
				if (!jsessionId.equals(httpSession.getId())) {
					/*httpSession.setAttribute("userId", userId);
					existMap.put("userId", jsessionId);*/
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
		
		return true;
	}

}
