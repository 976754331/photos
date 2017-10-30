package com.hoperun.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hoperun.util.JsonUtils;

import jodd.util.StringUtil;

//防止xss注入和sql注入
public class XssFilter implements Filter {
	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletRequest requestNew=request;
		//下面这句必须写，不写controller中不能获取P参数
		requestNew.getParameterMap();
		// 获取用户输入的json字符串
		String body = getBody((HttpServletRequest) request);
		// 将字符串转换为map；
		HashMap<String, String> paramMap = JsonUtils.deserialize(body, new TypeReference<HashMap<String, String>>() {
		});
		if(null!=paramMap) {
			HashMap<String, String> paramMapOver=new HashMap<String, String>();
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					paramMapOver.put(entry.getKey(), cleanXSS(entry.getValue()));
				}
			//将更新后的map转换成json
			body=JsonUtils.serialize(paramMapOver);
			chain.doFilter(getRequest(request,body), response);
		}else {
			chain.doFilter(new XssHttpServletRequestWrapper(
		                (HttpServletRequest) requestNew), response);
			//chain.doFilter(requestNew, response);
		}

		// Map paramMap = JsonUtil.json2Map(body);

	}

	// 获取request中的json串
	private String getBody(HttpServletRequest request) throws IOException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (null != bufferedReader) {
				bufferedReader.close();
			}
		}
		body = stringBuilder.toString();
		return body;
	}

	// 关键字过滤
	private String cleanXSS(String value) {
		if(StringUtil.isEmpty(value)) {
			return value;
		}
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}
    /**
     * 将post解析过后的request进行封装改写
     * @param request
     * @param body
     * @return
     */
    private ServletRequest getRequest(ServletRequest request ,String body){
      
        return new PostServletRequest((HttpServletRequest) request,body);

    }
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
		String s="你好";
		System.out.println(new String(s.getBytes("GBK")));
	}
}