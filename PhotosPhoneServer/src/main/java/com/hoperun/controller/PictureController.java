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
import com.hoperun.service.IPictureService;
import com.hoperun.service.ITypeService;

/**
 * 获取图片类型
 * @author Wang_wei
 *
 */
@Controller
@RequestMapping("/picture")
public class PictureController{
	
	@Autowired
	private IPictureService pictureService;
	
	/**
	 * 根据二级类型获取图片列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public ReturnInfo selectPicLists(HttpServletRequest request, String typeId) {
		ReturnInfo rtn = new ReturnInfo();
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		Map<String,String> params = new HashMap<String, String>();
		params.put("typeId", typeId);
		try {
			rtnList = pictureService.selectPicLists(params);
		} catch (ServiceException e) {
			ReturnInfo errRtn = new ReturnInfo();
			errRtn.setRtnCode(888);
			errRtn.setMsg("sql异常，执行操作失败");
		}
		rtn.setData(rtnList);
		return rtn;
	}
	
	
}
