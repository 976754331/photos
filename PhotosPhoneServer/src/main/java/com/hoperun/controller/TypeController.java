package com.hoperun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.exception.ServiceException;
import com.hoperun.model.vo.ReturnInfo;
import com.hoperun.service.ITypeService;

/**
 * 获取图片类型
 * @author Wang_wei
 *
 */
@Controller
@RequestMapping("/type")
public class TypeController{
	
	@Autowired
	private ITypeService typeService;
	
	/**
	 * 获取一级类型
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "first")
	@ResponseBody
	public ReturnInfo firstType(HttpServletRequest request) {
		ReturnInfo rtn = new ReturnInfo();
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		try {
			rtnList = typeService.selectFirstType();
		} catch (ServiceException e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		rtn.setData(rtnList);
		return rtn;
	}
	
	/**
	 * 获取二级类型
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "second")
	@ResponseBody
	public ReturnInfo secondType(HttpServletRequest request, String parentId) {
		ReturnInfo rtn = new ReturnInfo();
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		Map<String,String> params = new HashMap<String, String>();
		params.put("parentId", parentId);
		try {
			rtnList = typeService.selectSecondType(params);
		} catch (ServiceException e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		rtn.setData(rtnList);
		return rtn;
	}
	
	/**
	 * 创建一级分类
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "insertFirst")
	@ResponseBody
	public ReturnInfo insertFirstTypes(HttpServletRequest request, String typeName) {
		ReturnInfo rtn = new ReturnInfo();
		Map<String,String> params = new HashMap<String, String>();
		params.put("typeName", typeName);
		try {
			typeService.insertFirstTypes(params);
			rtn.setData("OK");
		} catch (ServiceException e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		return rtn;
	}
	
	/**
	 * 创建二级分类
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "insertSecond")
	@ResponseBody
	public ReturnInfo insertSecondTypes(HttpServletRequest request,String parentId, String typeName) {
		ReturnInfo rtn = new ReturnInfo();
		Map<String,String> params = new HashMap<String, String>();
		params.put("typeName", typeName);
		params.put("parentId", parentId);
		try {
			typeService.insertSecondTypes(params);
			rtn.setData("OK");
		} catch (ServiceException e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		return rtn;
	}
	
	
}
